package com.practical.shopcat

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.android.material.snackbar.Snackbar
import com.practical.shopcat.data.Resource
import com.practical.shopcat.databinding.ActivityMainBinding
import com.practical.shopcat.error.SEARCH_ERROR
import com.practical.shopcat.model.ShopByType
import com.practical.shopcat.model.ShopItem
import com.practical.shopcat.utils.*
import com.practical.shopcat.adapter.ShopsAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject

@AndroidEntryPoint
class MainActivity : BaseActivity()  {

    private lateinit var binding: ActivityMainBinding
//    private val mainActivityViewModel : MainActivityViewModel by viewModels()
    private lateinit var shopsAdapter: ShopsAdapter


    override fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager = LinearLayoutManager(this)
        binding.rvRecipesList.layoutManager = layoutManager
        binding.rvRecipesList.setHasFixedSize(true)
//        mainActivityViewModel.getRecipes()
        getdemoData()
    }


    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }


    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun showSearchError() {
//        mainActivityViewModel.showToastMessage(SEARCH_ERROR)
    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) View.GONE else View.VISIBLE
        binding.rvRecipesList.visibility = if (show) View.VISIBLE else View.GONE
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvRecipesList.toGone()
    }


    private fun showSearchResult(shopItem: ShopItem) {
//        mainActivityViewModel.openRecipeDetails(shopItem)
        binding.pbLoading.toGone()
    }

    private fun noSearchResult(unit: Unit) {
        showSearchError()
        binding.pbLoading.toGone()
    }


    private fun handleShopsList(status: Resource<ShopByType>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindListData(recipes = it) }
            is Resource.DataError -> {
//                showDataView(false)
//                status.errorCode?.let { mainActivityViewModel.showToastMessage(it) }
            }
        }
    }

    private fun bindListData(recipes: ShopByType) {
        if (!(recipes.recipesList.isNullOrEmpty())) {
//            shopsAdapter = ShopsAdapter(mainActivityViewModel, recipes.recipesList)
//            binding.rvRecipesList.adapter = shopsAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun navigateToDetailsScreen(navigateEvent: SingleEvent<ShopItem>) {
        navigateEvent.getContentIfNotHandled()?.let {
//            val nextScreenIntent = Intent(this, DetailsActivity::class.java).apply {
//                putExtra(RECIPE_ITEM_KEY, it)
//            }
//            startActivity(nextScreenIntent)
        }
    }

    override fun observeViewModel() {
//        observe(mainActivityViewModel.shopsLiveData, ::handleShopsList)
//        observe(mainActivityViewModel.shopSearchFound, ::showSearchResult)
//        observe(mainActivityViewModel.noSearchFound, ::noSearchResult)
//        observeEvent(mainActivityViewModel.openRecipeDetails, ::navigateToDetailsScreen)
//        observeSnackBarMessages(mainActivityViewModel.showSnackBar)
//        observeToast(mainActivityViewModel.showToast)
    }


    fun getdemoData() {

        AndroidNetworking.get(BASE_URL+ SHOPS_BY_CATEGORY)
            .setPriority(Priority.HIGH)
            .addPathParameter("shop_type","Restaurants")
            .addPathParameter("user_id","3160")
            .addPathParameter("latitude","21.19818")
            .addPathParameter("longitude","72.82422")
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("TagDataDemo","getdatafromIPv4 reps -- " + response)
//                    val gson = Gson()
//                    val tempResponse =
//                        gson.fromJson(java.lang.String.valueOf(response), GeoLocation::class.java)
//                    val tz = TimeZone.getTimeZone(tempResponse.timezone)
//                    val time: DateFormat = SimpleDateFormat("HH:mm:ss dd/MM/yyyy")
//                    time.setTimeZone(tz)
//
//                    ip_data?.visibility = View.VISIBLE
//                    hostname?.text = tempResponse.hostname
//                    city?.text = tempResponse.city
//                    region?.text = tempResponse.region
//                    country?.text = tempResponse.country
//                    location?.text = tempResponse.loc
//                    org?.text = tempResponse.org
//                    postal?.text = tempResponse.postal
//                    timezone?.text = tempResponse.timezone
//                    curr_time?.text = time.format(Date())

                }

                override fun onError(error: ANError) {
                    Log.e("Tag", "getdatafromIPv4 error -- " + error)

                    Toast.makeText(this@MainActivity,"No data found", Toast.LENGTH_SHORT).show()
                    // handle error
                }
            })

    }
}