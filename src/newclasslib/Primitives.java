package newclasslib;

import java.util.IntSummaryStatistics;

import stream.Album;

public class Primitives {
//int  Integer  LongFunction LongStream
	public static void printTrackLengthStatistics(Album album) {
		IntSummaryStatistics trackLengthStats = album.getTracks()
				.mapToInt(track -> track.getLength())//对基本类型int进行了特殊处理
				.summaryStatistics();

		System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
				trackLengthStats.getMax(), trackLengthStats.getMin(),
				trackLengthStats.getAverage(), trackLengthStats.getSum());
	}
}
