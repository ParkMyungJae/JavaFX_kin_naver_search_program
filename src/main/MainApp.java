package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import domain.ItemVO;
import domain.ResponseVO;

public class MainApp {
	public static void main(String[] args) {
		String ci = "pyomRbvLnCB1QxDWS_7b";
		String cs = "fne5AcnBoW";
		
		String url = "https://openapi.naver.com/v1/search/news.json";
		
		System.out.println("뉴스 내용을 입력하세요");
		Scanner in = new Scanner(System.in);
		String word = in.nextLine(); //한줄을 입력받고
		
		try {
			word = URLEncoder.encode(word, "UTF-8");
			url += "?query=" + word;
			
			URL urlIns = new URL(url);
			HttpURLConnection con = (HttpURLConnection) urlIns.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", ci);
			con.setRequestProperty("X-Naver-Client-Secret", cs);
			
			int resCode = con.getResponseCode();
			
			BufferedReader br;
			if(resCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine;
			StringBuffer resString = new StringBuffer();
			
			while((inputLine = br.readLine()) != null) {
				resString.append(inputLine);
			}
			
			br.close();
			
			String json = resString.toString();
			
			JsonParser parser = new JsonParser();
			
			JsonElement jsonElem = parser.parse(json);
			int total = jsonElem.getAsJsonObject().get("total").getAsInt();
			
			System.out.println(total);
			
//			Gson gson = new Gson();
//			ResponseVO res = gson.fromJson(json, ResponseVO.class);
//			List<ItemVO> items = res.getItems();
//			
//			for(ItemVO item : items) {
//				System.out.println(item.getTitle() + "[" + item.getPubDate() + "]");
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("API 호출중 오류 발생");
		}
	}
}