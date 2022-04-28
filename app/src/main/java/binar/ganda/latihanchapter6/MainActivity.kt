package binar.ganda.latihanchapter6

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import binar.ganda.latihanchapter6.adapter.FilmAdapter
import binar.ganda.latihanchapter6.viewmodel.FilmViewModel
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var adapterFilm : FilmAdapter
    lateinit var cont : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cont = this

        getFilmAsync().execute()

    }

    inner class getFilmAsync() : AsyncTask<Int, Void, String>(){

        lateinit var dialog : ProgressDialog

        override fun onPreExecute() {
            super.onPreExecute()
            dialog = ProgressDialog(cont)
            dialog.show()
        }

        override fun doInBackground(vararg p0: Int?): String? {
            initRecyclerView()
            return null
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            getDataFilm()
            dialog.dismiss()
        }

        @SuppressLint("NotifyDataSetChanged")
        private fun getDataFilm() {
            val viewModel = ViewModelProvider(this@MainActivity).get(FilmViewModel::class.java)

            viewModel.getLivedataFilm().observe(this@MainActivity) {
                if (it != null) {
                    adapterFilm.setDataFilm(it)
                    adapterFilm.notifyDataSetChanged()
                    Log.d("test", it.toString())
                }
            }
            viewModel.callApiFilm()
        }

        private fun initRecyclerView() {
            rv_films.layoutManager = LinearLayoutManager(cont)
            adapterFilm = FilmAdapter()
            rv_films.adapter = adapterFilm
        }

    }

}