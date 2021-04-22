package ru.nechay.zuzextest.testwork.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.nechay.zuzextest.testwork.models.ChildPage;
import ru.nechay.zuzextest.testwork.models.MainPage;

public class PageParser {
	private static ChildPage countLetters(ChildPage page, String regex) {
		Pattern pattern = Pattern.compile(regex);

		try {
			Document doc = Jsoup.connect(page.getSelfUrl())
			        .userAgent("Chrome/4.0.249.0 Safari/532.5")
			        .get();
			String mainText = doc.text();
			Matcher matcher = pattern.matcher(mainText);
			int count = 0;
			  while (matcher.find()) {
			       count++;
			    }
			  page.setCount(count);
			  
		}catch (IOException e) {
			//если была ошибка(404 например)
			page.setCount(-1);
		}

		return page;
	}
	
	
	public List<ChildPage> getChildPages(MainPage mainPage, int CountOfThreads){
		int numOfChild = 0;
		List<ChildPage> pages = new ArrayList<>();
		Document doc;
		try {
			doc = Jsoup.connect(mainPage.getSelfUrl())
			        .userAgent("Chrome/4.0.249.0 Safari/532.5")
			        .get();
			
			List<Callable<ChildPage>> callables = new ArrayList<Callable<ChildPage>>();
			for(Element el: doc.select("a")) {
				String link = el.attr("href");
				if(link.matches("(http|https)\\:\\/\\/.+\\..+")){
					ChildPage ch = new ChildPage(link,numOfChild++, mainPage.getId());
					pages.add(ch);
					callables.add(() -> countLetters(ch,"[A-Z]"));
				}
			}
			
			ExecutorService executor = Executors.newFixedThreadPool(CountOfThreads);
			
			
			//запускаем и ждем, пока все задачи завершатся
			executor.invokeAll(callables)
					.stream()
					.map(future ->{
						try {
							return future.get();
						}catch (Exception e) {
				            throw new IllegalStateException(e);
				        }
					})
					.forEach(x -> System.out.println(x));
			executor.shutdown();
						
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//удаляем все ошибочные страницы
		return pages	
				.stream()
				.filter(page -> page.getCount()>=0)
				.collect(Collectors.toList());
	}
}
