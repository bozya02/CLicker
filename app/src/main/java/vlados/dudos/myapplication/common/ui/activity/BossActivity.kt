package vlados.dudos.myapplication.common.ui.activity

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import vlados.dudos.myapplication.common.Case.bossImage
import vlados.dudos.myapplication.common.Case.bossRikardo
import vlados.dudos.myapplication.common.Case.updateCPC
import vlados.dudos.myapplication.common.Case.updateCPS
import vlados.dudos.myapplication.common.ui.models.Boss
import vlados.dudos.myapplication.databinding.ActivityBossBinding
import kotlin.random.Random.Default.nextInt


class BossActivity : AppCompatActivity() {

    private lateinit var b: ActivityBossBinding
    private lateinit var timer: CountDownTimer
    private var isWin = -1
    private var boss: Boss = Boss(100000000, -1,false,-1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityBossBinding.inflate(layoutInflater)
        setContentView(b.root)
        startBossFight()
        onClick()
        startTimer()
    }

    private fun startBossFight() {
        uploadBossImage()
        updateProgress()
    }

    private fun startMusic() {

    }

    private fun uploadBossImage() {
        Glide.with(this)
            .load(bossImage)
            .into(b.bossImage)
    }

    private fun onClick() {
        b.bossImage.setOnClickListener {
            val str = if (boss.isCpc) "click" else "sec"
            when (nextInt(4)) {
                0 -> {}
                else -> {
                    boss.bossHP -= 1
                    b.progressBar.progress-=1
                }
            }
            if (boss.bossHP == 0) {
                isWin = 1
                timer.cancel()

                val dialog = AlertDialog.Builder(this@BossActivity)
                    .setTitle("Let`s celebrate your victory!")
                    .setMessage("Your Cum /$str increased by ${boss.prizeAmount}!")
                    .setPositiveButton("Ok") { dialog, which ->
                        dialog.dismiss()
                        onBackPressed()
                    }
                    .show()
            }
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(boss.timeSec * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                b.timerText.text = ("Seconds Remaining: " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                if (boss.isCpc)
                    updateCPC(isWin * boss.prizeAmount)
                else updateCPS(isWin * boss.prizeAmount)
            }
        }
        timer.start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        timer.onFinish()
        updateProgress()
    }
    private fun updateProgress(){
        loadBoss(bossRikardo)
        b.progressBar.max = boss.bossHP
        b.progressBar.progress = boss.bossHP
    }
    private fun loadBoss(bigBoss: Boss){
        boss.bossHP = bigBoss.bossHP
        boss.prizeAmount = bigBoss.prizeAmount
        boss.isCpc = bigBoss.isCpc
        boss.timeSec = bigBoss.timeSec
    }
}