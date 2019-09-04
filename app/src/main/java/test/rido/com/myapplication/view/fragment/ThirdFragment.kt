package test.rido.com.myapplication.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import test.rido.com.myapplication.R

class ThirdFragment : BaseFragment() {
    private val TAG = ThirdFragment::class.java.simpleName

    override fun title(): String = "세번째"

    companion object {
        fun newInstance() = ThirdFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("rido", "onViewCreated!!")

        textForFragment.text = title()
    }
}