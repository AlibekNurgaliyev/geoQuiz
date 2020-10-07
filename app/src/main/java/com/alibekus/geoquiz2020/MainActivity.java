package com.alibekus.geoquiz2020;

import android.content.Context;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;


    private Question[] mQuestionbank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_amiricas, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;
    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionbank.length;
//                updateQuestion();
            }
        });
//        int question = mQuestionbank[mCurrentIndex].getTextResId();
//        mQuestionTextView.setText(question);


        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                checkAnswer(true);

            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                checkAnswer(false);

            }
        });

        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mCurrentIndex = (mCurrentIndex - 1) % mQuestionbank.length;
                mCurrentIndex--;
                updateQuestion();
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mCurrentIndex = (mCurrentIndex + 1) % mQuestionbank.length;
                mCurrentIndex++;
//                int question = mQuestionbank[mCurrentIndex].getTextResId();
//                mQuestionTextView.setText(question);
                updateQuestion();
            }
        });
        updateQuestion();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }


    private void updateQuestion() {
        int question = mQuestionbank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        mTrueButton.setEnabled(true);
        mFalseButton.setEnabled(true);
        if (mCurrentIndex == 0) {
            mPrevButton.setEnabled(false);
        } else {
            mPrevButton.setEnabled(true);
        }
        if (mCurrentIndex == mQuestionbank.length - 1) {
            mNextButton.setEnabled(false);
        } else {
            mNextButton.setEnabled(true);
        }
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionbank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        mTrueButton.setEnabled(false);
        mFalseButton.setEnabled(false);
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            progress++;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        if (mCurrentIndex == mQuestionbank.length - 1) {
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Result = " + progress * 100 / mQuestionbank.length + "%", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
        }
    }

}
