package com.hca.hcatask.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hca.hcatask.R
import com.hca.hcatask.databinding.RvItemQuestionsBinding
import com.hca.hcatask.model.QuestionItem

class QuestionsListAdapter constructor(private val block: (data: QuestionItem) -> Unit) :
        PagingDataAdapter<QuestionItem, QuestionsListAdapter.ImageViewHolder>(DataDifferntiator) {

    class ImageViewHolder(var inflateQuestionsBinding: RvItemQuestionsBinding) :
            RecyclerView.ViewHolder(inflateQuestionsBinding.root)

    object DataDifferntiator : DiffUtil.ItemCallback<QuestionItem>() {

        override fun areItemsTheSame(oldItem: QuestionItem, newItem: QuestionItem): Boolean {
            return oldItem.questionId == newItem.questionId
        }

        override fun areContentsTheSame(oldItem: QuestionItem, newItem: QuestionItem): Boolean {
            return oldItem.questionId == newItem.questionId
        }
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.inflateQuestionsBinding.item = getItem(position)
        holder.inflateQuestionsBinding.cardView.setOnClickListener {
            getItem(position)?.let { it1 -> block(it1) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
            ImageViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.rv_item_questions,
                            parent,
                            false
                    )
            )

}