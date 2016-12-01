package stream;

import static org.junit.Assert.assertEquals;
import static java.lang.Character.isDigit;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

import static java.util.Arrays.asList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

/**
 * @author yan96in
 *
 */

public class StreamExample {
	static List<Artist>  allArtists = new ArrayList<>();
	
	public static void  main(String[] args) {
		Artist a=new Artist("One", "London"),
				b=new Artist("Two","Peking"),
				c=new Artist("Three","Tokyo"),
				d=new Artist("Four","London");
		allArtists.add(a);allArtists.add(b);allArtists.add(c);allArtists.add(d);
		// 使用for循环来计算来自伦敦的艺术家人数，
		//问题：1.样板代码，2.改造成并行方式也很麻烦,需要修改每个for循环；3.多层循环使得代码阅读困难
		long count = 0;
		for (Artist artist : allArtists) {
			if (artist.isFrom("London")) {
				count++;
			}
		}
		// 使用迭代器计算来自伦敦的艺术家人数
		//这种被称之为外部迭代，问题：1.难以抽取操作，2.串行
		Iterator<Artist> iter = allArtists.iterator();
		while (iter.hasNext()) {//通过显式调用hasNext和next方法完成迭代
			Artist artist = iter.next();
			if (artist.isFrom("London")) {
				count++;
			}
		}
		// 使用 内部迭代 计算来自伦敦的艺术家人数，返回的不是控制迭代的Iterator对象，而是接口：Stream
		long count8 = allArtists.stream().filter(artist -> artist.isFrom("London")).count();//两步，但不需要两次循环
		//filter方法只过滤，不计数，是惰性求值
		allArtists.stream().filter(artist->{
			System.out.println(artist.getName());
			return artist.isFrom("London");
		})
		//输出艺术家的名字
		.count()//及早求值的方法，类似于builder模式的build方法，只有调用这个方法的时候才会真正的创建对象。
		;
	}

	// 常用的流操作
	// 1.collect
	@Test
	public void collect() {
		List<String> collected = Stream.of("a", "b", "c")// 由列表生成流
				.collect(Collectors.toList());// collect操作生成列表
		assertEquals(Arrays.asList("a", "b", "c"), collected);// 判断与预期是否一致
	}

	// 2.map
	@Test
	public void map() {
		// 使用for循环将字符串转换为大写
		List<String> list = new ArrayList<>();
		for (String s : Arrays.asList("a", "b", "hello")) {
			String uppercaseString = s.toUpperCase();
			list.add(uppercaseString);
		}
		//使用map操作将字符串转换为大写
		list=Stream.of("a","b","hello").map(string->string.toUpperCase())
		//接受String类型的参数，返回一个新的String。
		.collect(Collectors.toList());
		assertEquals(Arrays.asList("A", "B", "HELLO"), list);
	}
	
	//3.filter
	@Test
	public void filter(){
		//使用循环遍历列表，使用条件语句做判断
		List<String> beginningWithNumbers=new ArrayList<>();
		for(String value:asList("a","1abc","abc1")){
			if(isDigit(value.charAt(0))){
				beginningWithNumbers.add(value);
			}
		}	
		//函数式风格的
		beginningWithNumbers=Stream.of("a","1abc","abc1").filter(value->isDigit(value.charAt(0))).collect(toList());
		assertEquals(Arrays.asList("1abc"),beginningWithNumbers);
	}
	
	//4.flatMap
	@Test
	public void flatMap(){
		List<Integer> together=Stream.of(asList(1,2),asList(3,4))
				.flatMap(numbers->numbers.stream())//和map'方法一样是Function接口，返回值是Stream类型
				.collect(toList());
		assertEquals(asList(1,2,3,4),together);
	}
	
	//5. max和min
	@Test
	public void maxin(){
		List<Track> tracks=asList(new Track("bakai",524),
				new Track("Violets for your furs",378),
				new Track("Time Was",451));
		Track shortestTrack=tracks.stream()
				.min(Comparator.comparing(track->track.getLength()))//comparing是java8新提供的静态方法,接受一个函数，返回另一个函数。
				.get();//通过get方法可以取出Optional对象的值
		assertEquals(tracks.get(1),shortestTrack);
	}
	
	//6.reduce，实现从一组值生成一个值
	@Test
	public void reduce(){
		//使用命令式编程方式求和
		int acc1=0;
		for(Integer element:asList(1,2,3)){
			acc1=acc1+element;
		}
		//使用reduce求和
		int count =Stream.of(1,2,3).reduce(0, (acc,element)->acc+element);
		assertEquals(6,count);
		//展开reduce操作
		BinaryOperator<Integer> accumulator=(acc,elment)->acc+elment;
		count=accumulator.apply(
				accumulator.apply(
						accumulator.apply(0, 1), 
					2), 
				3);
	}
	//整合操作，待整理
	/*真的需要对外暴露一个List或Set对象吗？可能一个Stream工厂才是更好的选择。
	 * */
	public Set<String> findLongTracks(List<Album> albums){
		//待重构的代码
		Set<String> trackNames=new HashSet<>();
		for(Album album:albums){
			for(Track track:album.getTrackList()){
				if(track.getLength()>60){
					String name=track.getName();
					trackNames.add(name);
				}
			}
		}
		//return trackNames;流风格的代码
		return albums.stream()
				.flatMap(album->album.getTracks())
				.filter(track->track.getLength()>60)
				.map(track->track.getName())//
				.collect(toSet());
	}
	
	//多次调用
	/*多次调用和链式调用相比有如下缺点：
	 * 代码可读性差
	 * 效率差
	 * 代码充斥了一堆垃圾变量，只是用来保存中间结果
	 * 难于自动并行处理。
	 * 
	 * 
	 * */
}
