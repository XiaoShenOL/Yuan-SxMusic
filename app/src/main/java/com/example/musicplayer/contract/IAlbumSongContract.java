package com.example.musicplayer.contract;

import com.example.musicplayer.entiy.AlbumSong;

import java.util.List;


/**
 * Created by 残渊 on 2018/11/27.
 */

public interface IAlbumSongContract {
    interface Model{
        void getAlbumDetail(String id,int type); //获取专辑的更多信息
    }
    interface View{
        void setAlbumSongList(List<AlbumSong.DataBean.SongsBean> songList); //成功获取专辑歌曲后填充列表
        void showAlbumSongError();//获取专辑失败
        void showAlbumMessage(String name,String singer,String company,String desc); //展示专辑详细
        void showNetError(); //网络错误
    }
    interface Presenter{
        void getAlbumDetail(String id,int type); //获取专辑的更多信息
        void getAlbumDetailSuccess(int type,List<AlbumSong.DataBean.SongsBean>songList,
                                   String name,String singer,String company,String desc); //成功获取专辑信息
        void getAlbumDetailError(); //获取专辑信息失败
        void getAlbumError(); //接口出现问题
    }
}