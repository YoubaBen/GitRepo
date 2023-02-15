import 'react-native-gesture-handler';
import React, { Component } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createDrawerNavigator } from '@react-navigation/drawer';

import HomeScreen from './screens/home';
import LoginScreen from './screens/login';
import SignupScreen from './screens/signup';
import LogoutScreen from './screens/logout';
import ProfileScreen from './screens/profile';
import PostsScreen from './screens/posts';
import FriendsScreen from './screens/friends';
import SettingsScreen from './screens/settings';



const Drawer = createDrawerNavigator();

class App extends Component{
    render(){
        return (  


            <NavigationContainer>
                <Drawer.Navigator initialRouteName="Home">
                    <Drawer.Screen name="Home" component={HomeScreen} />
                    <Drawer.Screen name="Login" component={LoginScreen} />
                    <Drawer.Screen name="Signup" component={SignupScreen} />
                    <Drawer.Screen name="Logout" component={LogoutScreen} />
                    <Drawer.Screen name="Profile" component={ProfileScreen} />
                    <Drawer.Screen name="AddFriends" component={FriendsScreen} />
                    <Drawer.Screen name="Posts" component={PostsScreen} />
                   
                    
                </Drawer.Navigator>
                
            </NavigationContainer>
        );
    }
}

export default App;