package com.umutcansahin.todoapp.ui.chart

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.umutcansahin.todoapp.R
import com.umutcansahin.todoapp.databinding.FragmentChartBinding
import com.umutcansahin.todoapp.domain.uimodel.ToDoUIModel
import com.umutcansahin.todoapp.utils.BaseFragment
import com.umutcansahin.todoapp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_chart.*

@AndroidEntryPoint
class ChartFragment : BaseFragment(R.layout.fragment_chart) {

    private val binding by viewBinding(FragmentChartBinding::bind)

    private val viewModel: ChartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllToDo()
        observeEvent()
        initViews()
    }

    private fun observeEvent() {

        viewModel.entity.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                binding.messageTextView.visibility = View.VISIBLE
                binding.pieChart.visibility = View.GONE
            } else {
                setChartData(it)
                binding.pieChart.visibility = View.VISIBLE
                binding.messageTextView.visibility = View.GONE
            }
        })
    }

    private fun initViews() {
        binding.pieChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                e?.let {

                    val action = ChartFragmentDirections
                        .actionNavigationNotificationsToDetailChartFragment(it.data.toString())
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }

            override fun onNothingSelected() {}
        })

        bottomNavigationViewVisibility = View.VISIBLE
        toolbarVisibility = true
    }

    private fun setChartData(list: List<ToDoUIModel>) {

        var school = 0f
        var shopping = 0f
        var sport = 0f
        var business = 0f
        var schoolColor ="#FFFFFF"
        var shoppingColor = "#FFFFFF"
        var sportColor = "#FFFFFF"
        var businessColor = "#FFFFFF"

        list.forEach {
            when (it.type) {
                "School" -> {
                    school++
                    schoolColor = it.typeColor
                }
                "Business" -> {
                    business++
                    businessColor = it.typeColor
                }
                "Shopping" -> {
                    shopping++
                    shoppingColor = it.typeColor
                }
                "Sport" -> {
                    sport++
                    sportColor = it.typeColor
                }
            }
        }

        val pieEntry = mutableListOf<PieEntry>()
        pieEntry.add(PieEntry(school, "School", "School"))
        pieEntry.add(PieEntry(business, "Business", "Business"))
        pieEntry.add(PieEntry(sport, "Sport", "Sport"))
        pieEntry.add(PieEntry(shopping, "Shopping", "Shopping"))


        val pieDataSet = PieDataSet(pieEntry, "")
        pieDataSet.setColors(
            Color.parseColor(schoolColor),
            Color.parseColor(businessColor),
            Color.parseColor(sportColor),
            Color.parseColor(shoppingColor)
        )
        pieDataSet.valueTextSize = 30f
        pieDataSet.valueTextColor = Color.BLACK
        pieDataSet.formSize = 30f
        pieDataSet.form = Legend.LegendForm.CIRCLE
        val data = PieData(pieDataSet)
        pieChart.data = data
        pieChart.animateY(2000)

    }

}
