package test.rido.com.myapplication.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import test.rido.com.myapplication.view.fragment.BaseFragment
import test.rido.com.myapplication.view.fragment.SearchFragment
import test.rido.com.myapplication.view.fragment.SecondFragment
import test.rido.com.myapplication.view.fragment.ThirdFragment

class SimplePagerAdapter : FragmentPagerAdapter {


    private val list: ArrayList<BaseFragment> = ArrayList()

    constructor(fragmentManager: FragmentManager) : super(fragmentManager) {
        list.add(SearchFragment.newInstance())
        list.add(SecondFragment.newInstance())
        list.add(ThirdFragment.newInstance())
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].title()
    }

    override fun getItem(position: Int): Fragment {
        return list.get(position)
    }

    override fun getCount(): Int {
        return list.size
    }


}