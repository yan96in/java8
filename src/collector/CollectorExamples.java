package collector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import stream.Album;
import stream.Artist;

import static java.nio.charset.Charset.defaultCharset;
import static java.util.Comparator.comparing;
import static java.util.Map.Entry;
import static java.util.stream.Collectors.*;

public class CollectorExamples {

	public void toCollectionTreeset() {
		Stream<Integer> stream = Stream.of(1, 2, 3);
		stream.collect(toCollection(TreeSet::new));
	}
	//找出成员最多但乐队
	public Optional<Artist> biggestGroup(Stream<Artist> artists) {
		Function<Artist, Long> getCount = artist -> artist.getMembers().count();
		return artists.collect(maxBy(comparing(getCount)));
	}
	//将艺术家组成的流分成乐队和独唱歌手两部分
	public Map<Boolean, List<Artist>> bandsAndSolo(Stream<Artist> artists) {
		return artists.collect(partitioningBy(artist -> artist.isSolo()));
	}
	//功能同上，使用了方法引用
	public Map<Boolean, List<Artist>> bandsAndSoloRef(Stream<Artist> artists) {
		return artists.collect(partitioningBy(Artist::isSolo));
	}
	//使用主唱对专辑分组
	public Map<Artist, List<Album>> albumsByArtist(Stream<Album> albums) {
		return albums.collect(groupingBy(album -> album.getMainMusician()));
	}
	//使用简单方式求每个艺术家的专辑名
	public Map<Artist, Integer> numberOfAlbumsDumb(Stream<Album> albums) {
		Map<Artist, List<Album>> albumsByArtist = albums.collect(groupingBy(album -> album.getMainMusician()));

		Map<Artist, Integer> numberOfAlbums = new HashMap<>();
		for (Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
			numberOfAlbums.put(entry.getKey(), entry.getValue().size());
		}
		return numberOfAlbums;
	}
	//使用收集器计算每个艺术家的专辑数
	public Map<Artist, Long> numberOfAlbums(Stream<Album> albums) {
		return albums.collect(groupingBy(album -> album.getMainMusician(), counting()));
	}

	public Map<Artist, List<String>> nameOfAlbumsDumb(Stream<Album> albums) {
		Map<Artist, List<Album>> albumsByArtist = albums.collect(groupingBy(album -> album.getMainMusician()));

		Map<Artist, List<String>> nameOfAlbums = new HashMap<>();
		for (Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
			nameOfAlbums.put(entry.getKey(), entry.getValue().stream().map(Album::getName).collect(toList()));
		}
		return nameOfAlbums;
	}
	//使用收集器求每个艺术家的专辑名
	public Map<Artist, List<String>> nameOfAlbums(Stream<Album> albums) {
		return albums.collect(groupingBy(Album::getMainMusician, mapping(Album::getName, toList())));
	}
	
	public static Map<String, Long> countWords(Stream<String> words) {
		return words.collect(groupingBy(word -> word, counting()));
	}

	private static final Pattern SPACES = Pattern.compile("\\w+");

	public static Map<String, Long> countWordsIn(Path path) throws IOException {
		Stream<String> words = Files.readAllLines(path, defaultCharset()).stream()
				.flatMap(line -> SPACES.splitAsStream(line));

		return countWords(words);
	}

	public double averageNumberOfTracks(List<Album> albums) {
		return albums.stream().collect(averagingInt(album -> album.getTrackList().size()));
	}

}
