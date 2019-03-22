package com.example.musicplayer.presenter;

import android.support.design.widget.TabLayout;
import android.util.Log;

import com.example.musicplayer.base.BasePresenter;
import com.example.musicplayer.contract.IPlayContract;
import com.example.musicplayer.entiy.SeachSong;
import com.example.musicplayer.entiy.Song;
import com.example.musicplayer.model.PlayModel;
import com.example.musicplayer.util.MediaUtil;

import java.util.List;

/**
 * Created by 残渊 on 2018/10/26.
 */

public class PlayPresenter extends BasePresenter<IPlayContract.View> implements IPlayContract.Presenter {
    private static final String TAG = "PlayPresenter";
    private PlayModel mModel;

    public PlayPresenter(){
        mModel=new PlayModel(this);
    }

    @Override
    public void getSingerImg(String singer, String song,long duration) {
        mModel.getSingerImg(singer,song,duration);
    }

    @Override
    public void getLrc(String song, long duration) {
        mModel.getLrc(song,duration);
    }

    @Override
    public void getSingerImgSuccess(String ImgUrl) {
        if(isAttachView()){
            getMvpView().setSingerImg(ImgUrl);
        }
    }

    @Override
    public void getSongLrcSuccess(List<SeachSong.DataBean> dataBeans,long duration) {
        if(isAttachView()){
            Log.d(TAG, "getSongLrcSuccess: duration="+MediaUtil.formatLongToThree(duration));
            boolean isLrc =false;
            for(SeachSong.DataBean dataBean : dataBeans){
                if(dataBean.getTime() == MediaUtil.formatLongToThree(duration)){
                    isLrc = true;
                    getMvpView().showLrcMessage(dataBean.getLrc());
                    break;
                }
            }
            Log.d(TAG, "getSongLrcSuccess: "+isLrc);
            if(!isLrc) getMvpView().showLrcMessage(dataBeans.get(0).getLrc());
        }
    }

    @Override
    public void getSongLrcFail() {
        if(isAttachView()){
            getMvpView().showLrcMessage(null);
        }
    }

    @Override
    public void getSingerImgFail() {
        if(isAttachView()){
            getMvpView().setImgFail("获取歌手照片失败");
        }
    }

    @Override
    public void showNetWorkError() {

    }

    @Override
    public void queryLove(String songId) {
        mModel.queryLove(songId);
    }

    @Override
    public void saveToLove(Song song) {
        mModel.saveToLove(song);
    }

    @Override
    public void deleteFromLove(String songId) {
        mModel.deleteFromLove(songId);
    }

    @Override
    public void saveToLoveSuccess() {
        if(isAttachView()){
            getMvpView().saveToLoveSuccess();
        }
    }

    @Override
    public void showLove(boolean love) {
        if(isAttachView()){
            getMvpView().showLove(love);
        }
    }

    @Override
    public void deleteSuccess() {
        if(isAttachView()){
            getMvpView().sendUpdateCollection();
        }
    }
}
