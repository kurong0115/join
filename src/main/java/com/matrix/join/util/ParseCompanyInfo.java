package com.matrix.join.util;

import com.matrix.join.po.Company;
import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ParseCompanyInfo
 * @Description 爬取公司基本信息
 * @Author Administrator
 * @Date 2019/11/29 13:32
 * @Version 1.0
 */
public class ParseCompanyInfo {

	private static String cookie = "lastCity=101280600; _bl_uid=d0k0U24e9InrtFjzXxbFgyOmwRaO; __c=1574995699; __g=-; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1574130322,1574941792,1574995700; __l=l=%2Fwww.zhipin.com%2F&r=https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3D3QEEr4zSvzuYMTWB9Df5HlDe60wbSbexYl3GNBCFhDAiHz0ymx9HzNcayrWNKcej%26wd%3D%26eqid%3Db9e15a0e00014a14000000065de086c7&friend_source=0&friend_source=0; JSESSIONID=\"\"; toUrl=https%3A%2F%2Fwww.zhipin.com%2Fgongsi%2F%3Fka%3Dheader_brand; __a=41958594.1572227244.1574941792.1574995699.106.5.92.98; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1575024272; __zp_stoken__=6919LngXsPj%2F%2F0Q7t7985uBjoWYGSCMcDoKjw5LkdB5o%2BeT9se1GRS9p0Ti8Y55vs9YX%2BPgVak19naT9YA%2Blo4B7nw%3D%3D";
	private static BasicCookieStore basicCookieStore = new BasicCookieStore();

	/**
	 * 解析页面信息，转换成实体类
	 * @return
	 */
	public static List<Company> parseBaseInfo(){
		List<Company> list = new ArrayList<>();
		return list;
	}

	/**
	 * 设置请求头参数
	 * @param requestMethod
	 */
	public static void setRequestHeader(HttpRequestBase requestMethod){
		requestMethod.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
		requestMethod.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		requestMethod.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
		requestMethod.setHeader("accept-encoding", "gzip");
		requestMethod.setHeader("cookie", cookie);
	}

	public static String getCookie(HttpResponse response){
//		Header[] headers = response.getAllHeaders();
//		String info = null;
//		for (Header header: headers){
//			HeaderElement[] elements = header.getElements();
//			for (HeaderElement element: elements){
//				System.err.println(element.getName() + "---->" + element.getValue());
//				System.out.println();
//			}
//		}
//		return info;

		List<Cookie> cookies = basicCookieStore.getCookies();
		for (int i = 0; i < cookies.size(); i++){
			System.out.println(cookies.get(i).getName() + "--->" + cookies.get(i).getValue());
		}
		return null;
	}

	/**
	 * 设置cookie
	 * @param cookie
	 */
	public static void setCookie(String cookie){
		cookie = cookie;
	}

	/**
	 * 获取页面内容
	 * @param url
	 * @return
	 */
	public static String getContent(String url){
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(basicCookieStore).build();
		HttpGet get = new HttpGet(url);
		setRequestHeader(get);
		HttpResponse response = null;
		String content = null;
		try {
			response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			getCookie(response);
			content = EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 将页面信息写入文件
	 * @param content
	 * @param path
	 */
	public static void writeFile(String content, String path){
		File file = new File(path);
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content);
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null){
					bufferedWriter.close();
				}
				if (fileWriter != null){
					fileWriter.close();
				}
			} catch (Exception e){

			}
		}
	}

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis() >> 1);
		System.out.println(Integer.MAX_VALUE);
//		for (int i = 1; i<= 9; i++){
//			String content = getContent("https://www.zhipin.com/gongsi/?page=" + i + "&ka=page-" + i);
			String content = getContent("https://www.zhipin.com/gongsi/5e139930da054b4233R43w~~.html?ka=brand_list_company_0");
			writeFile(content, "d:/image/detail.html");
//		}
	}
}
