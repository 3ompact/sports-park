package com.mda.basics_lib.permission


import androidx.fragment.app.FragmentActivity
import com.mda.basics_lib.R
import com.mda.basics_lib.toast.ToastUtil
import com.permissionx.guolindev.PermissionX


/**
 * permission工具
 */
class PermissionUtil {

    /**
     * 请求单个权限
     */
    fun requestSinglePermission(
        fa: FragmentActivity,
        permission: String,
        permissionListener: PermissionListener
    ) {
        PermissionX.init(fa).permissions(permission).onExplainRequestReason { scope, deniedList ->
            scope.showRequestReasonDialog(deniedList,fa.getString(R.string.permission_explain_bl),fa.getString(R.string.know_bl))
        }.onForwardToSettings { scope, deniedList ->
            scope.showForwardToSettingsDialog(deniedList, fa.getString(R.string.go_permission_seeting_bl), "我已明白")
        }.request { allGranted, grantedList, deniedList ->
            if (allGranted) {
                permissionListener.onGranted()
            } else {
                ToastUtil.showLengthShort(fa.getString(R.string.permission_denied_bl)+deniedList)
                permissionListener.onDenied()
            }

        }
    }

    /**
     * 请求单个权限
     */
    fun requestMultiPermission(
        fa: FragmentActivity,
        permission: List<String>,
        permissionListener: PermissionListener
    ) {
        PermissionX.init(fa).permissions(permission).onExplainRequestReason { scope, deniedList ->
            scope.showRequestReasonDialog(deniedList,fa.getString(R.string.permission_explain_bl),fa.getString(R.string.know_bl))
        }.onForwardToSettings { scope, deniedList ->
            scope.showForwardToSettingsDialog(deniedList, fa.getString(R.string.go_permission_seeting_bl), "我已明白")
        }.request { allGranted, grantedList, deniedList ->
            if (allGranted) {
                permissionListener.onGranted()
            } else {
                ToastUtil.showLengthShort(fa.getString(R.string.permission_denied_bl)+deniedList)
                permissionListener.onDenied()
            }

        }
    }


    interface PermissionListener {
        fun onGranted()
        fun onDenied()
    }
}