package com.mvvm.code.ui.weather

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mvvm.code.R
import com.mvvm.code.databinding.ItemWeatherBinding
import com.mvvm.code.model.Weather

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    private lateinit var weatherList: List<Weather>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapter.ViewHolder {
        val binding: ItemWeatherBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_weather, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherAdapter.ViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int {
        return if (::weatherList.isInitialized) weatherList.size else 0
    }

    fun updatePostList(weatherList: List<Weather>) {
        this.weatherList = weatherList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = WeatherAdapterViewModel()

        fun bind(weather: Weather) {
            viewModel.bind(weather)
            binding.viewModel = viewModel
        }
    }
}