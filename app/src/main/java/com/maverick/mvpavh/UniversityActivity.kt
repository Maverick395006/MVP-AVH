package com.maverick.mvpavh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.maverick.mvpavh.adapter.UniversityAdapter
import com.maverick.mvpavh.contracts.MainActivityContract
import com.maverick.mvpavh.databinding.ActivityUniversityBinding
import com.maverick.mvpavh.model.MainModel
import com.maverick.mvpavh.network.api.ApiService
import com.maverick.mvpavh.network.model.UniversityDTO
import com.maverick.mvpavh.presenter.MainPresenter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UniversityActivity : AppCompatActivity(), MainActivityContract.View {

    @Inject
    lateinit var apiService: ApiService

    private lateinit var presenter: MainPresenter

    private lateinit var binding: ActivityUniversityBinding

    private lateinit var universityAdapter : UniversityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUniversityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this, MainModel(apiService))

        initView()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.getUniversity(newText.toString())
                return true
            }

        })
    }

    private fun initView() {
        universityAdapter = UniversityAdapter()
        binding.rvUniversity.adapter = universityAdapter
    }

    override fun onLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onSuccess(list: List<UniversityDTO>) {
        universityAdapter.addAll(list)
        binding.progressBar.visibility = View.GONE
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        binding.progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}