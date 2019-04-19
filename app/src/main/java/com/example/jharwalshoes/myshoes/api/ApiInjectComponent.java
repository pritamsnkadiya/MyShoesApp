package com.example.jharwalshoes.myshoes.api;

import com.example.jharwalshoes.myshoes.fragment.QrScannerFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiClient.class})
public interface ApiInjectComponent {
   /* void inject(ImageFragment fragment);
    void inject(BlureFragment fragment);
    void inject(LoginFragment fragment);*/
   void inject(QrScannerFragment fragment);
}