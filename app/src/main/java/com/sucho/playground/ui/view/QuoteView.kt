package com.sucho.playground.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.sucho.playground.R

class QuoteView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
  private val quoteTextView: TextView
  private val quoteImageView: ImageView

  init {
    LayoutInflater.from(context).inflate(R.layout.layout_quote_item, this, true)
    quoteTextView = findViewById(R.id.quote_text_view)
    quoteImageView = findViewById(R.id.quote_image_view)
  }

  fun setQuoteText(quote: String) {
    quoteTextView.text = quote
  }

  fun setKanyeImage(imageResId: Int) {
    quoteImageView.setImageResource(imageResId)
  }

}