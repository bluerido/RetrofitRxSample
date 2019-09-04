package test.rido.com.myapplication.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*
import test.rido.com.myapplication.BaseApplication
import test.rido.com.myapplication.R
import test.rido.com.myapplication.data.GitInfo
import test.rido.com.myapplication.network.data.Item
import test.rido.com.myapplication.network.data.User
import test.rido.com.myapplication.view.adapter.CustomRecyclerAdpater

class SearchFragment : BaseFragment() {
    val TAG = SearchFragment::class.java.simpleName

    override fun title(): String = "검색"
    private var disposable: Disposable? = null

    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {
        view_recycler_search.layoutManager = LinearLayoutManager(context)
    }

    private fun initEvent() {
        view_btn_search.setOnClickListener {
            Log.e(TAG, "view on click search")
            searchApi()
            hideKeyPad(it.context, view_edit_search)
        }
    }

    private fun hideKeyPad(context: Context, edt: EditText) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edt.windowToken, 0)
    }


    private fun searchApi() {
        val searchText = view_edit_search.text.toString().trim()
        val search = BaseApplication.apiClient.searchUser(searchText)
        disposable = search
            .map(this::generateInfoData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    setAdapter(it)

                }, {
                    Log.e(TAG, it.message)
                }, {
                    Log.e(TAG, "complete")
                })

//        search.enqueue(object : Callback<List<Repo>> {
//            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
//                Log.e(TAG, "onFailure")
//                Log.e(TAG, t.message)
//            }
//
//            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
//                Log.e(TAG, "onSuccess")
//                if (response.isSuccessful) {
//                    Log.e(TAG, response.body().toString())
//                }
//            }
//        })
    }

    private fun generateInfoData(user: User): ArrayList<GitInfo> {
        var infos: ArrayList<GitInfo> = ArrayList()
        for (data: Item in user.items) {
            infos.add(GitInfo(data.login, data.avatar_url))
        }

        return infos
    }

    private fun setAdapter (it: ArrayList<GitInfo>) {
        val adapter = CustomRecyclerAdpater(it)
        view_recycler_search.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable?.dispose()
    }
}