package com.merabk.moviesapplicationtm.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.merabk.moviesapplicationtm.databinding.RvItemBinding
import com.merabk.moviesapplicationtm.domain.model.ContentDomainModel
import com.merabk.moviesapplicationtm.util.loadImageWithGlide

class RvAdapter(
    private val templateClick: () -> Unit
) : ListAdapter<ContentDomainModel, RvAdapter.TvViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder = TvViewHolder(
        RvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        templateClick
    )

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) =
        holder.bind(getItem(position))

    class TvViewHolder(
        private val binding: RvItemBinding,
        private val templateClick: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(rvModel: ContentDomainModel) = with(binding) {
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500${rvModel.posterPath}")
                .centerCrop()
                .into(filmImage)

            filmName.text = "NAME: ${rvModel.originalName} "
            filmRating.text = "RATING: ${rvModel.voteAverage.toString()}"
            itemView.setOnClickListener {
                templateClick()
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<ContentDomainModel>() {

            override fun areContentsTheSame(
                oldItem: ContentDomainModel,
                newItem: ContentDomainModel
            ): Boolean = oldItem == newItem

            override fun areItemsTheSame(
                oldItem: ContentDomainModel,
                newItem: ContentDomainModel
            ): Boolean = oldItem.id == newItem.id
        }
    }
}