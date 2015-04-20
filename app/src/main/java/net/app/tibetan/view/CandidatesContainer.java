package net.app.tibetan.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.example.like.tibetan.MainActivity;
import com.example.like.tibetan.R;

import net.app.tibetan.input.SoftKeyBoard;

import java.util.List;

/**
 * Created by like on 15-4-18.
 */
public class CandidatesContainer extends RelativeLayout implements View.OnTouchListener{
    private ViewFlipper mFlipper;
    public  boolean mState=false;
    private ImageButton settingBtn;
    private Button setting_keyboard;
    private Button setting_edit;
    public  boolean flag=false;
    private SoftKeyBoard mService;
    private ImageButton setting_hide;
    private RelativeLayout cadidatesState;
    private RelativeLayout candidatesContent;
    private CandidateView candidateView;
    private Context context;
    public CandidatesContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        // TODO Auto-generated constructor stub
    }
    private void startAnimation()
    {
        this.mFlipper.showNext();
    }
    private void showPreviouse(){
        this.mFlipper.showPrevious();
    }
    private void stopAnimation()
    {
        this.mFlipper.stopFlipping();
    }
    public void setService(SoftKeyBoard paramInputMethodService)
    {
        this.mService = paramInputMethodService;
    }
    public void initialize(){
        this.mFlipper = ((ViewFlipper)findViewById(R.id.candidate_flipper));
        this.mFlipper.setMeasureAllChildren(true);
        cadidatesState=(RelativeLayout)findViewById(R.id.suggetion_util);
        candidatesContent=(RelativeLayout)findViewById(R.id.suggetion_symbol);
        settingBtn=(ImageButton)findViewById(R.id.setting_btn);
        setting_keyboard=(Button)findViewById(R.id.setting_keyboard);
        setting_edit=(Button)findViewById(R.id.setting_edit);
        setting_hide=(ImageButton)findViewById(R.id.setting_hide_keyboard);
        setting_hide.setOnTouchListener(this);
        setting_edit.setOnTouchListener(this);
        setting_keyboard.setOnTouchListener(this);
        settingBtn.setOnTouchListener(this);

    }
    public void showCandidates(List<String> list){
        if(mFlipper.getCurrentView()==cadidatesState&&list!=null){
            mFlipper.showNext();
        }
        candidateView=(CandidateView)mFlipper.getChildAt(1).findViewById(
                R.id.candidate_view1);
        if(list!=null&&list.size()!=0){
            candidateView.setSuggestions(list, true, true);
        }else{
            Log.i("5555555555555555", "555555555555555");
            if(mFlipper.getCurrentView()==candidatesContent){
                Log.i("66666666666666","666666666");
                mFlipper.showNext();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
    	/*if(event.getAction()==MotionEvent.ACTION_DOWN){
    		startAnimation();
    	}*/
        return false;
    }
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // TODO Auto-generated method stub
        if(view==settingBtn){
            if(event.getAction()== MotionEvent.ACTION_DOWN){
                Intent intent=new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                context.startActivity(intent);

            }
        }
        if(view==setting_keyboard){
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                ((SoftKeyBoard)this.mService).changeKeyboard();
            }
        }
        if(view==setting_edit){

        }
        if(view==setting_hide){
            ((SoftKeyBoard)this.mService).hideKeyboard();
            return false;
        }
        return false;
    }
}