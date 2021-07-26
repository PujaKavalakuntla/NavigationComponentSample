package com.example.navigationcomponentsample.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.navigationcomponentsample.BirdsAdapter
import com.example.navigationcomponentsample.R
import com.example.navigationcomponentsample.api.BirdsApi
import com.example.navigationcomponentsample.model.Birds
import com.example.navigationcomponentsample.databinding.FragmentProfileBinding
import com.example.navigationcomponentsample.ui.ViewPagerActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    var sharedPreferences: SharedPreferences? = null
    private var fragmentProfileBinding: FragmentProfileBinding? = null
    private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @SuppressLint("SetTextI18n", "CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentProfileBinding.bind(view)
        fragmentProfileBinding = binding
        navController = Navigation.findNavController(view)
        sharedPreferences =
            activity?.getSharedPreferences("myapp", AppCompatActivity.MODE_PRIVATE)

        val listView = binding.listViewBirds

        val rf: Retrofit = Retrofit.Builder()
            .baseUrl(BirdsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val birdsApi = rf.create(BirdsApi::class.java)
        val call: Call<ArrayList<Birds?>?>? = birdsApi.geBirdsData()
        call?.enqueue(object : Callback<ArrayList<Birds?>?> {
            override fun onResponse(
                call: Call<ArrayList<Birds?>?>?,
                response: Response<ArrayList<Birds?>?>
            ) {
                val birdsLists = response.body()
                val birdsAdapter =
                    BirdsAdapter(activity!!.applicationContext, R.layout.birds_list, birdsLists)
                listView.adapter = birdsAdapter
                listView.divider = null

                listView.onItemClickListener =
                    AdapterView.OnItemClickListener { _, _, _, _ ->
                        startActivity(Intent(activity, ViewPagerActivity::class.java))
                    }
            }

            override fun onFailure(call: Call<ArrayList<Birds?>?>?, t: Throwable?) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            logOut()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logOut() {
        sharedPreferences!!.edit().clear().apply()
        sharedPreferences!!.edit().putBoolean("login", false).apply()
        navController?.navigate(R.id.action_profileFragment_to_welcomeFragment)
    }

    override fun onDestroyView() {
        fragmentProfileBinding = null
        super.onDestroyView()
    }

}