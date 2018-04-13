import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import firebase from 'firebase';
import { firebaseConfig } from './firebaseCredentials';
import { HomePage } from '../pages/home/home';
import { Unsubscribe } from '@firebase/util';

import { TabsLogin} from '../pages/tabs_login/tabs';

@Component({
  templateUrl: 'app.html'
})

export class MyApp {

  //rootPage:any = TabsLogin;
  /*if you create an account and start using the app for a while,
  then close it, when you come back to use the app again,
  it should understand that you’re already a user and send you to the home page,
  instead of having you log in again. */
  rootPage: any;

  constructor(
    platform: Platform,
    statusBar: StatusBar,
    splashScreen: SplashScreen
  ) {
    firebase.initializeApp(firebaseConfig);

    /* user if already logged in, then go to Home page instead of login page.
    onAuthStateChanged() function looks for that object to see if a user already exists or not.
    If the user doesn’t exist, the user variable will be null,
    But if there’s a user, it will return the user’s information */
    const unsubscribe: Unsubscribe = firebase.auth().onAuthStateChanged(user => {
      if (user) {
        this.rootPage = HomePage;
        unsubscribe();
      } else {
        this.rootPage = 'LoginPage';
        /*
        The unsubscribe(); is because we’re telling the function to call itself once it redirects the user,
         this is because the onAuthStateChanged() returns the unsubscribe function for the observer.
         It is a good practice, if you create a function to edit the user’s email or password
         firebase will ask you to re-authenticate, and when you do,
         it will move you out of the page and send you to the HomePage if the observer is still active.
         */
        unsubscribe();
      }
    });

    platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      statusBar.styleDefault();
      splashScreen.hide();
    });
  }
}
