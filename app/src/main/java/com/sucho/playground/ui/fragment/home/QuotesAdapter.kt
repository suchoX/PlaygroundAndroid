package com.sucho.playground.ui.fragment.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sucho.domain.model.KanyeQuoteWithImage
import com.sucho.domain.model.SwansonQuoteWithImage
import com.sucho.domain.model.WalterWhiteQuoteWithImage
import com.sucho.playground.R
import com.sucho.playground.databinding.ItemQuoteBinding
import com.sucho.playground.utils.CustomClickListener
import com.sucho.playground.utils.OnDoubleClickListener

class QuotesAdapter : Adapter<QuotesViewHolder>() {
  private lateinit var inflater: LayoutInflater
  private lateinit var context: Context
  var kanyeQuoteWithImage: KanyeQuoteWithImage? = null
  var swansonQuoteWithImage: SwansonQuoteWithImage? = null
  var walterWhiteQuoteWithImage: WalterWhiteQuoteWithImage? = null

  lateinit var quoteClickListener: QuoteClickListener

  override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
    super.onAttachedToRecyclerView(recyclerView)
    inflater = LayoutInflater.from(recyclerView.context)
    this.context = recyclerView.context
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder =
    QuotesViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_quote, parent, false))

  override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
    when (position) {
      0 -> kanyeQuoteWithImage?.let {
        holder.itemQuoteBinding.quoteTextView.text = it.quote
        holder.itemQuoteBinding.quoteImageView.setImageResource(it.imageResId)
        holder.itemQuoteBinding.root.setOnClickListener(OnDoubleClickListener(object : CustomClickListener {
          override fun onSingleClickEvent(view: View?) {}
          override fun onDoubleClickEvent(view: View?) {
            quoteClickListener.saveQuote(it.quote, KanyeQuoteWithImage.getType(), it.imageResId)
          }
        }))
      }
      1 -> swansonQuoteWithImage?.let {
        holder.itemQuoteBinding.quoteTextView.text = it.quote
        holder.itemQuoteBinding.quoteImageView.setImageResource(it.imageResId)
        holder.itemQuoteBinding.root.setOnClickListener(OnDoubleClickListener(object : CustomClickListener {
          override fun onSingleClickEvent(view: View?) {}
          override fun onDoubleClickEvent(view: View?) {
            quoteClickListener.saveQuote(it.quote, SwansonQuoteWithImage.getType(), it.imageResId)
          }
        }))
      }
      2 -> walterWhiteQuoteWithImage?.let {
        holder.itemQuoteBinding.quoteTextView.text = it.quote
        holder.itemQuoteBinding.quoteImageView.setImageResource(it.imageResId)
        holder.itemQuoteBinding.root.setOnClickListener(OnDoubleClickListener(object : CustomClickListener {
          override fun onSingleClickEvent(view: View?) {}
          override fun onDoubleClickEvent(view: View?) {
            quoteClickListener.saveQuote(it.quote, WalterWhiteQuoteWithImage.getType(), it.imageResId)
          }
        }))
      }
    }
  }

  override fun getItemCount(): Int = 3
}

class QuotesViewHolder constructor(val itemQuoteBinding: ItemQuoteBinding) :
  ViewHolder(itemQuoteBinding.root)

interface QuoteClickListener {
  fun saveQuote(quote: String, type: Int, imgRes: Int)
}