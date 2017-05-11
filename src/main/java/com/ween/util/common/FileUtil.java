package com.ween.util.common;

import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件工具类.
 * 提供常用的文件操作，包括文件复制、移动、删除、下载等操作，以及文件缓存和对这些文件的存取
 * 放入的临时文件建议在合适的时间清除掉
 * @title FileUtil
 * @author Gavin
 */
public final class FileUtil {
	//存放临时文件
	private static ConcurrentHashMap<String, File> fileCache;
	/**
	 * 私有构造器。
	 */
	private FileUtil(){}
	/**
	 * 从缓存中获取文件
	 * @param key
	 * @return
	 */
	public static File getFileInCache(String key){
		if(fileCache!=null&&fileCache.containsKey(key)){
			return fileCache.get(key);
		}else{
			return null;
		}
	}

	/**
	 * 将文件放入缓存，并返回文件对应的key
	 * @param file
	 * @return true放入成功，false已存在
	 */
	public static boolean addFileInCache(String key,File file){
		if(fileCache==null){
			fileCache=new ConcurrentHashMap<String, File>();
		}
		if(fileCache.contains(key)){
			return false;
		}else{
			fileCache.put(key, file);
			return true;
		}
	}

	/**
	 * 获得项目根目录
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getRootLocation(HttpServletRequest request) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("/");
		return path;
	}

	public static void deleteFile(String filepath) {
		if(filepath!=null){
			deleteFile(new File(filepath));
		}
	}

	public static void deleteFile(File file) {
		if(file!=null&&file.exists()){
			if (file.isDirectory()) {
				File[] children = file.listFiles();
				if (children != null && children.length != 0) {
					for (File child:children) {
						deleteFile(child);
					}
				}
			}
			file.delete();
		}
	}
	/**
	 * 从缓存中删除文件
	 * @param key
	 * @return
	 */
	public static boolean deleteFileInCache(String key){
		if(fileCache!=null&&fileCache.contains(key)){
			fileCache.remove(key);
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 将一个文件转换成字节数组
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] getByte(File file) throws IOException {
        byte[] bytes = new byte[(int)file.length()];
        FileInputStream fis=new FileInputStream(file);
        fis.read(bytes);
        fis.close();
        return bytes;
	}

	/**
	 * 将一个文件转换成字节数组
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] getByte(InputStream in) throws IOException {
		byte[] bs=new byte[in.available()];
		in.read(bs);
		return bs;
	}
	/**
	 * 将字节码转换为文件
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	public static File getFile(InputStream in,String filename) throws IOException {
		if(in!=null&&filename!=null){
			File file=new File(filename);
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream fos=new FileOutputStream(file);
			byte[] temp=new byte[1024];
			int length=0;
			while((length=in.read(temp))>0){
				fos.write(temp,0,length);
			}
			fos.flush();
			fos.close();
			return file;
		}else{
			return null;
		}
	}

	/**
	 * 将字节码转换为文件
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	public static File getFile(byte[] bytes,String filename) throws IOException {
		if(bytes!=null&&filename!=null&&bytes.length>0){
			File file=new File(filename);
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream fos=new FileOutputStream(file);
			fos.write(bytes);
			fos.flush();
			fos.close();
			return file;
		}else{
			return null;
		}
	}

	/**
	 * 获取网络文件
	 * @param sourceURL 网络文件路径
	 * @param destination 目标文件
	 * @throws IOException
	 */
	public static void getFile(String sourceURL,File destination) throws IOException {
		URL u = new URL(sourceURL);
		FileUtils.copyURLToFile(u, destination);
	}

	/**
	 * 下载文件
	 * @param filename
	 * @param File
	 * @param response
	 * @throws IOException
	 */
	public static void downloadFile(String filename,File file, HttpServletResponse response) throws IOException {
		downloadFile(filename,new FileInputStream(file),response);
	}
	/**
	 * 下载文件
	 * @param filename
	 * @param InputStream
	 * @param response
	 * @throws IOException
	 */
	public static void downloadFile(String filename,InputStream is, HttpServletResponse response) throws IOException {
		if(is!=null){
			response.reset();
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", -1);
			filename = new String(filename.getBytes("gbk"), "ISO8859-1");
			response.setHeader( "Content-Disposition", "attachment;filename=" + filename );
			OutputStream out = response.getOutputStream();
			byte[] data=new byte[1024];
			int length=0;
			while((length=is.read(data))>0){
				out.write(data, 0, length);
			}
			out.close();
			is.close();
		}
	}
	/**
	 * 下载文件
	 * @param filename
	 * @param byte[]
	 * @param response
	 * @throws IOException
	 */
	public static void downloadFile(String filename,byte[] data, HttpServletResponse response) throws IOException {
		downloadFile(filename,new ByteArrayInputStream(data),response);
	}

	public static String toUtf8String(HttpServletRequest request, String filename){
        String agent=request.getHeader("user-agent");
        String str = "default";
        try {
            if (agent.indexOf("MSIE") != -1) {
                str = URLEncoder.encode(filename, "UTF-8");
                if(str.length()>150){
                    str=new String(filename.getBytes("GBK"),"ISO-8859-1");
                }
            }else{
            	str = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
            }
        }catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return str;
    }

	/**
	 * 两个文件是否有交叉.
	 * 1.如果两个文件如果两个都是文件并且两个文件都存在即为交叉
	 * 2.如果一个为文件一个为文件夹，即为交叉
	 * 3.如果都为文件夹，如果该文件夹内有相对文件重复，即为交叉，如果includeRoot为false，那忽略根目录
	 * @param srcFile
	 * @param toCoverFile
	 * @param includeRoot
	 * @return
	 */
	public static boolean cross(File srcFile,File toCoverFile,boolean includeRoot){
		return false;
	}

	/**
	 * 复制文件
	 * @param srcFile 源文件路径
	 * @param destFile 目标文件路径
	 * @param overlay 是否覆盖，如果目标文件已存在，并且该参数为false，那么返回false
	 * @return 是否复制成功，源文件不存在、复制失败、文件已存在并且没选择覆盖则返回false。
	 */
	public static boolean copyFile(String srcFile,String destFile,boolean overlay){
		if(srcFile==null&&destFile==null){
			return false;
		}else{
			return copyFile(new File(srcFile), new File(destFile),overlay);
		}
	}

	/**
	 * 复制文件
	 * @param srcFile 源文件
	 * @param destFile 目标文件
	 * @param overlay 是否覆盖，如果目标文件已存在，并且该参数为false，那么返回false
	 * @return 是否复制成功，源文件不存在、复制失败、文件已存在并且没选择覆盖则返回false。
	 */
	public static boolean copyFile(File srcFile, File destFile,boolean overlay) {
		return copyOrRemoveFile(srcFile,destFile,overlay,true);
	}

	//复制或移动文件
	private static boolean copyOrRemoveFile(File srcFile, File destFile,boolean overlay,boolean isCopy) {
        // 判断源文件是否存在
        if (srcFile==null||!srcFile.exists()) {
            return false;
        }
        // 如果目标文件所在目录不存在，则创建目录
        if (!destFile.exists()&&!destFile.getParentFile().exists()) {
            // 目标文件所在目录不存在
            if (!destFile.getParentFile().mkdirs()) {
                // 复制文件失败：创建目标文件所在目录失败
                return false;
            }
        }

        if(srcFile.isDirectory()){
        	boolean complated=true;
        	if(destFile.exists()){
        		if(!destFile.isDirectory()){
        			if(overlay){
            			deleteFile(destFile);
            		}else{
            			return false;
            		}
        		}
        	}else{
        		complated=destFile.mkdir();
        	}
        	if(complated){
        		File[] children=srcFile.listFiles();
        		boolean allComplate=true;
        		for(File child:children){
        			allComplate=copyOrRemoveFile(child,new File(destFile.getAbsoluteFile()+"\\"+child.getName()),overlay,isCopy);
        			if(!allComplate){
        				break;
        			}
        		}
        		return allComplate;
        	}else{
        		return false;
        	}
        }else{
        	boolean complated=true;
        	if(destFile.exists()){
        		if(overlay){
        			deleteFile(destFile);
        		}else{
        			complated=false;
        		}
        	}
        	if(complated){
        		if(isCopy){
        			InputStream in = null;
        			OutputStream out = null;
        			try {
        				in = new FileInputStream(srcFile);
        				out = new FileOutputStream(destFile);
        				byte[] buffer = new byte[1024];
        				int byteread = 0; // 读取的字节数
        				while ((byteread = in.read(buffer)) != -1) {
        					out.write(buffer, 0, byteread);
        				}
        				return true;
        			} catch (FileNotFoundException e) {
        				return false;
        			} catch (IOException e) {
        				return false;
        			} finally {
        				try {
        					if (out != null)
        						out.close();
        					if (in != null)
        						in.close();
        				} catch (IOException e) {
        					e.printStackTrace();
        				}
        			}
        		}else{
        			return srcFile.renameTo(destFile);
        		}
        	}else{
        		return false;
        	}
        }
    }

	/**
	 * 移动文件
	 * @param srcFile 源文件
	 * @param destFile 目标文件
	 * @param overlay 是否覆盖，如果目标文件已存在，并且该参数为false，那么返回false
	 * @return 是否复制成功，源文件不存在、移动失败、文件已存在并且没选择覆盖则返回false。
	 */
	public static boolean removeFile(File srcFile, File destFile,boolean overlay) {
		return copyOrRemoveFile(srcFile,destFile,overlay,false);
	}

	/**
	 * 移动文件
	 * @param srcFile 源文件
	 * @param destFile 目标文件
	 * @param overlay 是否覆盖，如果目标文件已存在，并且该参数为false，那么返回false
	 * @return 是否复制成功，源文件不存在、移动失败、文件已存在并且没选择覆盖则返回false。
	 */
	public static boolean removeFile(String srcFile,String destFile,boolean overlay){
		if(srcFile==null&&destFile==null){
			return false;
		}else{
			return removeFile(new File(srcFile), new File(destFile),overlay);
		}
	}
}
