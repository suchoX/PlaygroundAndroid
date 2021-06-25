package com.sucho.playground.ui.fragment.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sucho.domain.model.KanyeQuoteWithImage
import com.sucho.domain.model.SwansonQuoteWithImage
import com.sucho.domain.model.WalterWhiteQuoteWithImage
import com.sucho.playground.R
import com.sucho.playground.databinding.LayoutQuoteItemBinding
import com.sucho.playground.ui.fragment.home.QuotesAdapter.QuotesViewHolder

class QuotesAdapter: Adapter<QuotesViewHolder>() {
  private lateinit var inflater: LayoutInflater
  private lateinit var context: Context
  var kanyeQuoteWithImage: KanyeQuoteWithImage? = null
  var swansonQuoteWithImage: SwansonQuoteWithImage? = null
  var walterWhiteQuoteWithImage: WalterWhiteQuoteWithImage? = null

  override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
    super.onAttachedToRecyclerView(recyclerView)
    inflater = LayoutInflater.from(recyclerView.context)
    this.context = recyclerView.context
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder =
    QuotesViewHolder(DataBindingUtil.inflate(inflater, R.layout.layout_quote_item, parent, false))

  override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
    when (position) {
      0 -> kanyeQuoteWithImage?.let {
        holder.layoutQuoteItemBinding.quoteTextView.text = it.quote
        holder.layoutQuoteItemBinding.quoteImageView.setImageResource(it.imageResId)
      }
      1 -> swansonQuoteWithImage?.let {
        holder.layoutQuoteItemBinding.quoteTextView.text = it.quote
        holder.layoutQuoteItemBinding.quoteImageView.setImageResource(it.imageResId)
      }

      2 -> walterWhiteQuoteWithImage?.let {
        holder.layoutQuoteItemBinding.quoteTextView.text = it.quote
        holder.layoutQuoteItemBinding.quoteImageView.setImageResource(it.imageResId)
      }
    }
  }

  override fun getItemCount(): Int = 3

  class QuotesViewHolder constructor(val layoutQuoteItemBinding: LayoutQuoteItemBinding) :
    ViewHolder(layoutQuoteItemBinding.root)
}