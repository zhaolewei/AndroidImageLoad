package com.zlw.utils.imageloader.helper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

public class FileHelper {
	/**
	 * Bitmap 转File 类型
	 * 
	 * @param bm
	 * @param fileName
	 * @throws IOException
	 */
	public static void bitmapToFile(Bitmap bm, String filePath, String fileName)
			throws IOException {
		if (bm == null) {
			Log.i("cache", "bm==null:");
			return;
		}
		File dirFile = new File(filePath);
		if (!dirFile.exists()) {
			dirFile.mkdir();
		}
		File myCaptureFile = new File(filePath + fileName);
		Log.i("cache", "myCaptureFile:" + myCaptureFile);
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(myCaptureFile));
		bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
		bos.flush();
		bos.close();
	}

	/**
	 * drawable转Bitmap
	 * 
	 * @author zlw QQ:739043667
	 * @param
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {

		Bitmap bitmap = Bitmap
				.createBitmap(
						drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight(),
						drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
								: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		// canvas.setBitmap(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());

		drawable.draw(canvas);

		return bitmap;

	}

	/**
	 * --获取SD卡根目录 》权限： android.permission.WRITE_EXTERNAL_STORAGE
	 * 
	 * @author zlw QQ:739043667
	 * @param
	 * @return
	 */
	public static String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
		}
		return sdDir.toString();
	}

	/**
	 * -获应用缓存目录 》权限： android.permission.WRITE_EXTERNAL_STORAGE
	 * 
	 * @author zlw QQ:739043667
	 * @param
	 * @return
	 */
	public static String getDiskCacheDir(Context context, String uniqueName) {
		String cachePath;
		if (context == null) {
			Log.i("cache", "context == null");
			return null;
		}

		// 如果sd卡存在并且没有被移除
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {
			cachePath = context.getExternalCacheDir().getPath();
		} else {
			cachePath = context.getCacheDir().getPath();
		}
		return cachePath + File.separator + uniqueName;
	}

	/**
	 * 获取某一文件夹的总大小ByFile
	 */
	public static double getDirSizeByFile(File file) {
		// 判断文件是否存在
		if (file.exists()) {
			// 如果是目录则递归计算其内容的总大小
			if (file.isDirectory()) {
				File[] children = file.listFiles();
				double size = 0;
				for (File f : children)
					size += getDirSizeByFile(f);
				return size;
			} else {// 如果是文件则直接返回其大小,以“兆”为单位
				double size = (double) file.length() / 1024 / 1024;
				return size;
			}
		} else {
			System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
			return 0.0;
		}
	}

	/**
	 * 获取某一文件夹的总大小Path 单位 M
	 */
	public static double getDirSizeByPath(String path) {
		return getDirSizeByFile(new File(path));
	}

	/**
	 * 删除某一文件夹及其文件 
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除某一文件夹中的文件（不包含文件夹） 
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
}
