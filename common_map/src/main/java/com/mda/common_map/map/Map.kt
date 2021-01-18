package com.mda.common_map.map

import android.location.Location
import android.os.Bundle
import com.amap.api.maps.AMap
import com.amap.api.maps.AMapOptions
import com.amap.api.maps.LocationSource
import com.amap.api.maps.MapView
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.Marker
import com.amap.api.maps.model.MarkerOptions
import com.amap.api.maps.model.MyLocationStyle
import com.mda.common_map.bean.MapMarker
import com.mda.common_map.const.Consts

/**
 * 地图基础配置类
 */
class Map() : LocationSource, LocationSource.OnLocationChangedListener {
    var INTERVAL: Long = 2000
    private lateinit var mMapView: MapView
    private var aMap: AMap? = null

    /**
     * 创建地图和基础配置
     */
    fun onCreate(savedInstanceState: Bundle, mapView: MapView) {
        mMapView = mapView
        mMapView.onCreate(savedInstanceState)

        if (aMap == null) {
            aMap = mMapView.getMap()
        }
        var locationStyle = MyLocationStyle()
        locationStyle.interval(INTERVAL)
        aMap!!.myLocationStyle = locationStyle
        aMap!!.isMyLocationEnabled = true

        var uiSetting = aMap!!.uiSettings
        uiSetting.isZoomControlsEnabled = true
        uiSetting.isCompassEnabled = false
        uiSetting.isMyLocationButtonEnabled = true
        aMap!!.isMyLocationEnabled = true
        aMap!!.setLocationSource(this)
        uiSetting.isScaleControlsEnabled = true
        uiSetting.logoPosition = AMapOptions.LOGO_POSITION_BOTTOM_RIGHT

        uiSetting.isZoomGesturesEnabled = true
        uiSetting.isScrollGesturesEnabled = true
        //其他手势暂时不支持
//        uiSetting.isRotateGesturesEnabled = true
//        uiSetting.isTiltGesturesEnabled = true
//        uiSetting.setAllGesturesEnabled(true)


    }

    //设置地图交互移动动画
    fun changeCameraPositionInteractive() {

    }

    /**
     * 绘制标记点
     */

    fun drawMarkPosition(
        lats: ArrayList<Double>,
        lons: ArrayList<Double>,
        titleDesc: ArrayList<String>
    ) {
        var ll: LatLng
        var marker: Marker
        for (i in 0 until lats.size) {
            ll = LatLng(lats[i], lons[i])
            aMap!!.addMarker(
                MarkerOptions().position(ll).title(titleDesc[i]).snippet("dDefaultMarker")
            )
        }

    }

    /**
     * 绘制标记点
     */
    fun drawMarkPosition(mapMarkers: ArrayList<MapMarker>) {
        var ll: LatLng
        var marker: Marker
        for (i in 0 until mapMarkers.size) {
            ll = LatLng(mapMarkers[i].lat, mapMarkers[i].lon)
            aMap!!.addMarker(
                MarkerOptions().position(ll).title(mapMarkers[i].title).snippet("dDefaultMarker")
            )
        }

    }


    fun onSaveInstanceState(outState: Bundle, mapView: MapView) {
        mMapView.onSaveInstanceState(outState)
    }

    fun onDestroy(mapView: MapView) {
        mMapView.onDestroy()
    }

    fun onResume(mapView: MapView) {
        mMapView.onResume()
    }

    fun onPause(mapView: MapView) {
        mMapView.onPause()
    }

    override fun activate(p0: LocationSource.OnLocationChangedListener?) {
    }

    override fun deactivate() {
    }

    override fun onLocationChanged(p0: Location?) {
    }


}