package com.matrix.join.util;

import com.matrix.join.common.EducationEnum;
import com.matrix.join.common.ScaleEnum;
import com.matrix.join.common.StageEnum;
import com.matrix.join.common.WorkExperienceEnum;
import com.matrix.join.dto.CompanyDTO;
import com.matrix.join.po.Company;
import com.matrix.join.po.CompanyDetail;
import com.matrix.join.po.CompanyImage;
import com.matrix.join.po.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
        try {
            CompanyDTO companyDTO = parseCompany("d:/tmp/info/detail.html");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从本地文件读取dom信息
     * @param path
     * @return
     */
	public static Document getDocument(String path){
		Document document = null;
		try {
			document = Jsoup.parse(new File(path), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

    /**
     * 从url中读取dom信息
     * @param url
     * @return
     */
	public static Document getDocument(URL url){
	    Document document = null;
        try {
            document = Jsoup.parse(url, 10000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

	public static String getJobUrl(Document document){
		Element element = document.getElementsByAttributeValue("class", "company-tab").first();
		Element ka = element.getElementsByAttributeValue("ka", "company-jobs").first();
		StringBuilder sb = new StringBuilder();
		sb.append("https://www.zhipin.com");
		sb.append(ka.attr("href"));
		return sb.toString();
	}

	/**
	 * 爬取一个公司下的图片、详情信息、职位信息
	 * @param path
	 * @return
	 */
	public static CompanyDTO parseCompany(String path) throws MalformedURLException {
		CompanyDTO companyDTO = new CompanyDTO();
		Document document = getDocument(path);
		Company company = parseCompany(document);
		List<CompanyImage> images = getImages(document);
		CompanyDetail companyDetail = parseCompanyDetail(document);
		companyDTO.setCompanyDetail(companyDetail);
		companyDTO.setCompany(company);
		companyDTO.setList(images);
		String jobUrl = getJobUrl(document);
        System.out.println(jobUrl);
        document = getDocument("d:/tmp/info/job_list.html");
        Job job = parseJob(document);
        System.out.println(job);
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

	public static Job parseJob(Document document) {
		Job job = new Job();
        Element primaryInfo = document.getElementsByAttributeValue("class", "info-primary").first();
        job.setName(primaryInfo.getElementsByTag("h1").first().text());
        job.setBenefit(primaryInfo.getElementsByAttributeValue("class", "tag-all job-tags").first().html());
        String p = primaryInfo.getElementsByTag("p").first().html();
        System.out.println(p);
        String[] split = p.split("<em class=\"dolt\"></em>");
        for (int i = 0; i < split.length; i++) {
            if (i == 0){
                job.setName(split[i]);
            } else if (i == 1) {
                Integer code = WorkExperienceEnum.code(split[i]);
                if (code != null){
                    job.setWorkExperience(code.byteValue());
                }
            } else {
                job.setEducation(EducationEnum.code(split[i]).byteValue());
            }
        }
        return job;
	}
}
