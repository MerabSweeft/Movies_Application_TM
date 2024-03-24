package com.merabk.moviesapplicationtm.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.merabk.moviesapplicationtm.databinding.ItemDetailBinding
import com.merabk.moviesapplicationtm.domain.model.ProductionCompaniesDomainModel
import com.merabk.moviesapplicationtm.util.loadImageWithGlide

class PhotosAdapter : ListAdapter<ProductionCompaniesDomainModel, PhotosAdapter.TvViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder = TvViewHolder(
        ItemDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) =
        holder.bind(getItem(position))

    class TvViewHolder(
        private val binding: ItemDetailBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photoModel: ProductionCompaniesDomainModel) = with(binding) {
            root.loadImageWithGlide(photoModel.logoPath)
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<ProductionCompaniesDomainModel>() {

            override fun areContentsTheSame(
                oldItem: ProductionCompaniesDomainModel,
                newItem: ProductionCompaniesDomainModel
            ): Boolean = oldItem == newItem

            override fun areItemsTheSame(
                oldItem: ProductionCompaniesDomainModel,
                newItem: ProductionCompaniesDomainModel
            ): Boolean = oldItem.id == newItem.id
        }
    }
}