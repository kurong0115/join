package com.matrix.join.util;

import com.matrix.join.entity.CompanyEntity;
import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
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

	private static String cookie = "uab_collina=157715497230803602367748; lastCity=101280100; _bl_uid=emk5g4717U52tmcgR698yygy486t; __c=1577154971; __g=-; __l=l=%2Fwww.zhipin.com%2Fweb%2Fcommon%2Fsecurity-check.html%3Fseed%3Dk0ZSgCagif%252FKhcw8HHPeZUSiew8vwelZDD6H7DGDUcw%253D%26name%3D058ad436%26ts%3D1577154971622%26callbackUrl%3D%252Fgongsir%252F5e139930da054b4233R43w%257E%257E.html%26srcReferer%3D&r=&friend_source=0&friend_source=0; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1576417569,1577154973; __a=65409055.1576417563.1576417599.1577154971.19.3.15.19; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1577166039; __zp_stoken__=cdcfxthucrXQ9BSHReE3wcldvSWORT2V5i0Rlxfh3nBxKmz7xZgH7sdXV6j3%2Fu1OWnkxkPzRRXC1PGT2XxFnW37wkDhPYbXdtc9KDnbZVZnGJYMtzTbe9GjieJtE47n7VmCN";
	private static BasicCookieStore basicCookieStore = new BasicCookieStore();

	/**
	 * 解析页面信息，转换成实体类
	 * @return
	 */
	public static List<CompanyEntity> parseBaseInfo(){
		List<CompanyEntity> list = new ArrayList<>();
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
		//requestMethod.setHeader("refer",  "https://www.zhipin.com/gongsi/37f2888188aaedbe1XN92dq9.html?ka=brand_list_company_1");
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
			String content = getContent("https://www.zhipin.com/gongsir/5e139930da054b4233R43w~~.html");
			writeFile(content, "d:/tmp/info/job.html");
//		}
	}
}
