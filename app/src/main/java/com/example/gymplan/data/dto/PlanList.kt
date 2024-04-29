package com.example.gymplan.data.dto
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class PlanList(val list: List<Plan>) : Parcelable
