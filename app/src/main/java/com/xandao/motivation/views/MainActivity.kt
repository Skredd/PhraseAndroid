package com.xandao.motivation.views

import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.xandao.motivation.R
import com.xandao.motivation.mocks.Mock
import com.xandao.motivation.util.MotivationConstants
import com.xandao.motivation.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val mMock = Mock()

    private var mFilter: Int = MotivationConstants.PHRASE_FILTER.ALL
    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mSecurityPreferences = SecurityPreferences(this)

        setListener()
        handleFilter(R.id.imageAll)
        refreshPhrase()
        verifyUserName()
    }

    override fun onClick(v: View) {
        val id = v.id
        val listId = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageSun)
        if (id in listId) {
            handleFilter(id)
        } else if (id == R.id.buttonNewPhrase) {
            refreshPhrase()
        }
    }

    private fun setListener() {
        imageAll.setOnClickListener(this)
        imageSun.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        buttonNewPhrase.setOnClickListener(this)
    }

    private fun handleFilter(id: Int) {
        imageAll.setImageResource(R.drawable.ic_all_unselected)
        imageHappy.setImageResource(R.drawable.ic_happy_unselected)
        imageSun.setImageResource(R.drawable.ic_sun_unselected)
        if (id === R.id.imageAll) {
            mFilter = MotivationConstants.PHRASE_FILTER.ALL
            imageAll.setImageResource(R.drawable.ic_all_selected)
        } else if (id == R.id.imageHappy) {
            mFilter = MotivationConstants.PHRASE_FILTER.HAPPY
            imageHappy.setImageResource(R.drawable.ic_happy_selected)

        } else {
            mFilter = MotivationConstants.PHRASE_FILTER.SUN
            imageSun.setImageResource(R.drawable.ic_sun_selected)

        }
    }

    private fun refreshPhrase() {
        textPhrase.text = mMock.getPhrase(mFilter)
    }

    private fun verifyUserName() {
        textUserName.text = mSecurityPreferences.getStoredString(MotivationConstants.KEY.PERSON_NAME)
    }
}
