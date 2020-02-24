import {NativeModules} from 'react-native';
const { RNMoPubAd } = NativeModules;

module.exports = {
    initializeAdSDK: (adUnitId: string, shouldShowGDPR: bool) => RNMoPubAd.initializeAdSDK(adUnitId, shouldShowGDPR)
};