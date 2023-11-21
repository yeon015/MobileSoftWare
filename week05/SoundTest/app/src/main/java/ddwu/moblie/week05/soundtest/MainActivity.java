package ddwu.moblie.week05.soundtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SoundPool soundPool;
    int sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        //사운드
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //(3)아래 있던 걸 위로 가져왔을 때.
////        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
////        int sound = soundPool.load(this, R.raw.dingdong, 1);
//
//        //(4) 얘는 소리가 더 큼.
//        AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
//                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                .build();
//
//        soundPool = new SoundPool.Builder()
//                .setMaxStreams(5)
//                .setAudioAttributes(audioAttributes)
//                .build();
//
//        sound = soundPool.load(this, R.raw.dingdong, 1);
//        //but, 얘도 에러 날 수 있음. onCreate는 화면 만드는 것인데 파일이 로딩하는데 오래걸리면 파일이 오랫동안 안열릴 수 있음.
//        //따라서 멀티 쓰레드 만들기. 로딩하는 쓰레드는 로딩하고 화면 작업은 화면 작업하고. 따로따로.


        //진동
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        //사운드
//        //얘를 사용! (1)
////        AudioAttributes audioAttributes = new AudioAttributes.Builder()
////                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
////                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
////                .build();
////
////        SoundPool soundPool = new SoundPool.Builder()
////                .setMaxStreams(5)
////                .setAudioAttributes(audioAttributes)
////                .build();
//
//        //(2)
////        SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0); // 예전 꺼라 실행안됨
////        int sound = soundPool.load(this, R.raw.dingdong, 1);  //이 두줄 main으로 올리면 실행 됨!
//
//        soundPool.play(sound, 1, 1, 0, 0, 1);
//        //(2)하면 실행이 안됨. why? 누르는 순간 사운드 파일 로딩. 파일이 크면 누르는 순간 로딩이 끝나지 못함. 따라서 로딩 안된
//        //상태에서 재생이 되어버리니 소리가 안날 수 있음. 그래서 미리 위에서 로딩해놓고 여기서 재생하니 소리 남.


        //진동. 진동 기능은 스마트폰 내부에 있는 작은 모터 사용. 전기 사용하는 것. 모터 계속 돌아갈 경우 배터리 낭비, 폰에 문제.
        //따라서 앱이 안드로이드 운영체제한테 허락 받아야함.
        //maaifests->AndroidMainifest.xml(이력서 같은 파일) 여기에 uses-permission 넣어야함.
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);
    }
}