package com.sucho.playground.ui.fragment.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sucho.data.db.entity.QuoteEntity
import com.sucho.playground.R
import com.sucho.playground.databinding.ItemSavedQuoteBinding

class SavedQuotesAdapter : Adapter<SavedQuotesViewHolder>() {
  private lateinit var inflater: LayoutInflater
  private lateinit var context: Context

  private var savedQuotes: ArrayList<QuoteEntity> = ArrayList()

  override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
    super.onAttachedToRecyclerView(recyclerView)
    inflater = LayoutInflater.from(recyclerView.context)
    this.context = recyclerView.context
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedQuotesViewHolder =
    SavedQuotesViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_saved_quote, parent, false))

  override fun onBindViewHolder(holder: SavedQuotesViewHolder, position: Int) {
    holder.itemSavedQuoteBinding.quoteTextView.text = savedQuotes[position].quote
    holder.itemSavedQuoteBinding.quoteImageView.setImageResource(savedQuotes[position].imgRes)
  }

  override fun getItemCount(): Int = savedQuotes.size

  fun setSavedQuotes(savedQuotes: List<QuoteEntity>) {
    this.savedQuotes.clear()
    this.savedQuotes.addAll(savedQuotes)
  }

  fun clearSavedQuotes() {
    val size = savedQuotes.size
    savedQuotes.clear()
    notifyItemRangeRemoved(0, size)
  }

}

class SavedQuotesViewHolder constructor(val itemSavedQuoteBinding: ItemSavedQuoteBinding) :
  ViewHolder(itemSavedQuoteBinding.root)