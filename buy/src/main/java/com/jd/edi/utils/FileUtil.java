package com.jd.edi.utils;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class FileUtil {
	private static final int BUF_SIZE = 1024;
	
	private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);
	
	private FileUtil() {
	}

	/**
	 * 构造输入的两个路径片断为组合路径
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String joinPath(String s1, String s2) {
		File f = new File(s1, s2);
		return f.getPath().replaceAll("\\\\", "/");
	}

	/**
	 * 以byteArray的形式读文件的全部内容
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static byte[] readFileAsByteArray(String path)throws Exception{
		File f = new File(path);
		if (f.exists()) {

			try (ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
					BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));) {
				byte[] buffer = new byte[BUF_SIZE];
				int len = 0;
				while (-1 != (len = in.read(buffer, 0, BUF_SIZE))) {
					bos.write(buffer, 0, len);
				}
				return bos.toByteArray();
			} catch (FileNotFoundException e) {
				throw new Exception("找不到文件：" + path, e);
			} catch (IOException e) {
				throw new Exception("读取文件失败：" + path, e);
			}
		}
		return new byte[0];
	}

	/**
	 * 以byteArray的形式写入文件
	 * 
	 * @param path
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static void writeByteArrayToFile(String path, byte[] data) throws Exception {
		File file = new File(path);
		
		try (FileOutputStream fo = new FileOutputStream(file);){
			fo.write(data);
		} catch (IOException e) {
			throw new Exception("读取文件失败：" + path, e);
		}
	}

	/**
	 * 创建文件夹路径
	 * 
	 * @param filePath
	 */
	public static void creatFilePath(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 拷贝文件
	 * 
	 * @param filePath
	 */
	public static void copyFile(File sourceFile, File targetFile) throws IOException {

		try (FileInputStream inputStream = new FileInputStream(sourceFile);
				BufferedInputStream inBuff = new BufferedInputStream(inputStream);

				FileOutputStream outputStream = new FileOutputStream(targetFile);
				BufferedOutputStream outBuff = new BufferedOutputStream(outputStream);) {
			// 缓冲数组
			byte[] b = new byte[BUF_SIZE];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getSuffixName(String fileName) {
		int lastPointIndex = fileName.lastIndexOf('.');
		if (lastPointIndex != -1) {
			return fileName.substring(lastPointIndex + 1, fileName.length());
		}

		return "";
	}

	public static String getFileName(String filePath) {
		if (StringUtils.isNotEmpty(filePath)) {
			String name = filePath.replaceAll("\\\\", "/");
			if (name.indexOf('/') != -1) {
				return name.substring(name.lastIndexOf('/') + 1);
			}
		}
		return filePath;
	}
	
	public static String getFileDir(String filePath){
		if (StringUtils.isNotEmpty(filePath)) {
			String name = filePath.replaceAll("\\\\", "/");
			if (name.lastIndexOf('/') != -1) {
				return name.substring(0, name.lastIndexOf('/'));
			}
		}
		return filePath;
	}

	public static void rename(String fileName){
		File file = new File(fileName);
		File toFile = new File(fileName + "." + TimeUtil.format(new Date(), TimeUtil.FORMAT_COMPACT));
		file.renameTo(toFile);
	}
	
	/**
	 * 文件大小单位转换
	 * @param size
	 * @return
	 */
	public static String convertSize(long size) {
        if(size<1024) {
            return (size + "Bytes");
        }else if(size<1024*1024) {
            return ((new DecimalFormat("0.00")).format(Float.valueOf(size)/(1024)) + "KB");
        }else if(size<1024*1024*1024) {
            return ((new DecimalFormat("0.00")).format(Float.valueOf(size)/(1024*1024)) + "MB");
        }else {
            return ((new DecimalFormat("0.00")).format(Float.valueOf(size)/(1024*1024*1024)) + "GB");
        }
    }
	
	/**
	 * 文件列表排序
	 * @param list
	 */
	public static void sortByFileName(List<Map<String, Object>> list) {
	    String IS_DIR ="isDir";
	    Collections.sort(list, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                if(o2.get(IS_DIR).equals(o1.get(IS_DIR))) {
                    return o1.get("name").toString().compareTo(o2.get("name").toString());
                }
                return o2.get(IS_DIR).toString().compareTo(o1.get(IS_DIR).toString());
            }
        });
	}
	
    /**
     * 获取文件存储路径
     * /存储目录/月份/时间戳.后缀
     * @param dir		基路径
     * @param originalFileName	上传文件名
     * @param saveOriginalName	保持用原文件名
     * @return
     */
    public static String getFileSavePath(String dir, String originalFileName, boolean saveOriginalName){
		String month = TimeUtil.format(new Date(), "yyyy-MM");
		String filePath = FileUtil.joinPath(dir, month);
		String fileName = "";
		if(saveOriginalName){
			fileName = originalFileName;
		}
		else{
			fileName = System.currentTimeMillis() + "." + getSuffixName(originalFileName);
		}
		return FileUtil.joinPath(filePath, fileName);
    }
    
    /**
	 * 写文件流
	 */
	public static void write(File outFile, String contents) {
		BufferedWriter writer = null;
		try {
			if (contents == null) {
				contents = "";
			}
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdirs();
			}
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile), "UTF-8"));// UTF-8 without
																// BOM
			writer.write(contents);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			if (writer != null) {
				try {
					writer.flush();
				} catch (IOException e) {
					LOG.error(e.getMessage());
				}
				try {
					writer.close();
				} catch (IOException e) {
					LOG.error(e.getMessage());
				}
			}
		}
	}
    
}
