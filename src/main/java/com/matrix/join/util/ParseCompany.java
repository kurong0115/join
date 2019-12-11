package com.matrix.join.util;

import com.matrix.join.common.ScaleEnum;
import com.matrix.join.common.StageEnum;
import com.matrix.join.dto.CompanyDTO;
import com.matrix.join.po.Company;
import com.matrix.join.po.CompanyDetail;
import com.matrix.join.po.CompanyImage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ParseCompany
 * @Description 爬取公司全部信息
 * @Author Administrator
 * @Date 2019/11/29 19:10
 * @Version 1.0
 */
public class ParseCompany {

	public static void main(String[] args) {
		CompanyDTO companyDTO = parseCompany("d:/image/detail.html");
	}

	public static Document getDocument(String path){
		Document document = null;
		try {
			document = Jsoup.parse(new File(path), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

	public static String parseJob(Document document){
		Element element = document.getElementsByAttributeValue("class", "company-tab").first();
		Element ka = element.getElementsByAttributeValue("ka", "company-jobs").first();
		StringBuilder sb = new StringBuilder();
		sb.append("https://www.zhipin.com");
		sb.append(ka.attr("href"));
		return sb.toString();
	}

	public static CompanyDTO parseCompany(String path){
		CompanyDTO companyDTO = new CompanyDTO();
		Document document = getDocument(path);
		Company company = parseCompany(document);
		List<CompanyImage> images = getImages(document);
		CompanyDetail companyDetail = parseCompanyDetail(document);
		companyDTO.setCompanyDetail(companyDetail);
		companyDTO.setCompany(company);
		companyDTO.setList(images);
		System.out.println(parseJob(document));
		System.out.println(companyDTO);
		return companyDTO;
	}

	public static List<CompanyImage> getImages(Document document){
		List<CompanyImage> images = new ArrayList<>();
		Element slider = document.getElementsByAttributeValue("class", "slider-main").first();
		Elements elements = slider.getElementsByTag("img");
		for (Element element: elements){
			CompanyImage image = new CompanyImage();
			int index = element.attr("src").indexOf("?");
			image.setUrl(element.attr("src").substring(0, index));
		}
		return images;
	}

	public static Company parseCompany(Document document){
		Company company = new Company();
		Elements elements = document.getElementsByAttributeValue("class", "info-primary");
		Element first = elements.first();
		Element img = first.getElementsByTag("img").first();
		int index = img.attr("src").indexOf("?");

		// 设置图标
		company.setIcon(img.attr("src").substring(0, index));
		Elements info = first.getElementsByAttributeValue("class", "info");
		// 设置名称
		company.setName(info.first().child(0).text());
		String[] split = info.first().child(1).html().split("<em class=\"dolt\"></em>");
		// 设置产业
		company.setIndustry(split[2]);
		// 设置人数
		company.setScale(ScaleEnum.getCode(split[1]));
		// 设置融资情况
		company.setStage(StageEnum.getCode(split[0]));
		String introduce = document.getElementsByAttributeValue("class", "job-detail").first().getElementsByAttributeValue("class", "text fold-text").first().html();
		index = introduce.indexOf("<a ");
		introduce = introduce.substring(0, index);
		company.setIntroduce(introduce);
		return company;
	}

	public static CompanyDetail parseCompanyDetail(Document document){
		CompanyDetail companyDetail = new CompanyDetail();
		Element business = document.getElementsByAttributeValue("class", "job-sec company-business").first();
		Elements li = business.getElementsByTag("li");
		companyDetail.setCorporator(li.first().text().replaceAll("法人代表：", ""));
		companyDetail.setRegisteredFund(li.get(1).text().replaceAll("注册资本：", ""));
		companyDetail.setCreateTime(li.get(2).text().replaceAll("成立时间：", ""));

		Elements auto = business.getElementsByAttributeValue("class", "col-auto");
		companyDetail.setType(auto.get(0).text().replaceAll("企业类型：", ""));
		companyDetail.setStatus(auto.get(1).text().replaceAll("经营状态：", ""));
		companyDetail.setRegisteredAddress(auto.get(2).text().replaceAll("注册地址：", ""));
		companyDetail.setUniformCreditCode(auto.get(3).text().replaceAll("统一信用代码：", ""));
		companyDetail.setScope(auto.get(4).text().replaceAll("经营范围：", ""));
		return companyDetail;
	}
}
