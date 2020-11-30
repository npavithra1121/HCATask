package com.hca.hcatask.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hca.hcatask.R
import com.hca.hcatask.databinding.RvItemAnswerBinding
import com.hca.hcatask.model.AnswerItem
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class AnswersListAdapter @Inject constructor(): ListAdapter<AnswerItem, AnswersListAdapter.ImageViewHolder>(DataDifferntiator) {

    class ImageViewHolder(var inflateAnswersBinding: RvItemAnswerBinding) :
        RecyclerView.ViewHolder(inflateAnswersBinding.root)

    object DataDifferntiator : DiffUtil.ItemCallback<AnswerItem>() {
        override fun areItemsTheSame(oldItem: AnswerItem, newItem: AnswerItem): Boolean {
            return oldItem.questionId == newItem.questionId
        }

        override fun areContentsTheSame(oldItem: AnswerItem, newItem: AnswerItem): Boolean {
            return oldItem.questionId == newItem.questionId
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rv_item_answer,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.inflateAnswersBinding.item =getItem(position).also {
            it.body = it.body.replace("<p>","").replace("</p>","")
        }
    }
}