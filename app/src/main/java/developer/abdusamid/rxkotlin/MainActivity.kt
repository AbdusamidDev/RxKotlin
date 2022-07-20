package developer.abdusamid.rxkotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import developer.abdusamid.rxkotlin.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchTextObservable = createButtonObservable()
        searchTextObservable.subscribe {
            binding.tv.text = it
            Log.d("MainActivity", it)
        }

//        Observable - obektlarni eshitib turadigan xususiyat
        binding.nextActivity.setOnClickListener {
            val intent = Intent(this, FlowableActivity::class.java)
            startActivity(intent)
        }
        searchTextObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.plus(it) //map keladigan ma'lumotni o'zgartirib beradi
            }
            .filter {
                it.length == 5 //filter shart berish uchun
            }
            .debounce(
                2L,
                TimeUnit.SECONDS //2 sekundan keyin ishla! Faqat bacground uchun viewlar uchun emas xato beradi viewlar uchun handlar yoki boshqasidan ishlatish kerak
            )
            .subscribe {
                binding.tv.text = it
                Log.d("M", it)
            }
    }

    private fun createButtonObservable(): Observable<String> {
        return Observable.create { emitter -> //emitter RxJava ichida bor xabarlarni yuborib turadigan classi
            //editText texti o'zgarganda
            binding.edtText.addTextChangedListener {
                emitter.onNext(binding.edtText.text.toString())
            }
            //button bosilganda
            binding.btn.setOnClickListener {
                emitter.onNext(binding.edtText.text.toString())
            }

            emitter.setCancellable(null) //emitter ishini bajarib bo'lgandan keyin cancel bo'lishi
        }
    }
}