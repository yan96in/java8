package solid;

import java.util.stream.IntStream;

public class SingleResponsibilityPrinciple {

	public static interface PrimeCounter {
		long countPrimes(int upTo);
	}

	public static class ImperativeSingleMethodPrimeCounter implements PrimeCounter {
		@Override
		//计算质数个数，一个方法里面塞进了俩个职责
		public long countPrimes(int upTo) {
			long tally = 0;//计数器
			for (int i = 1; i < upTo; i++) {
				boolean isPrime = true;
				for (int j = 2; j < i; j++) {
					if (i % j == 0) {
						isPrime = false;
					}
				}
				if (isPrime) {
					tally++;
				}
			}
			return tally;
		}
	}

	public static class ImperativeRefactoredPrimeCounter implements PrimeCounter {
		@Override
		//计算质数的方法
		public long countPrimes(int upTo) {
			long tally = 0;
			for (int i = 1; i < upTo; i++) {
				if (isPrime(i)) {
					tally++;
				}
			}
			return tally;
		}
		//isPrime
		private boolean isPrime(int number) {
			for (int i = 2; i < number; i++) {
				if (number % i == 0) {
					return false;
				}
			}
			return true;
		}
	}

	public static class FunctionalPrimeCounter implements PrimeCounter {
		//使用集合流重构质数计算程序
		@Override
		public long countPrimes(int upTo) {
			return IntStream.range(1, upTo).filter(this::isPrime).count();
		}

		private boolean isPrime(int number) {
			return IntStream.range(2, number).allMatch(x -> (number % x) != 0);
		}
	}
	//并行运行基于集合流的质数计数程序
	public static class ParallelFunctionalPrimeCounter implements PrimeCounter {
		@Override
		public long countPrimes(int upTo) {
			return IntStream.range(1, upTo).parallel().filter(this::isPrime).count();
		}

		private boolean isPrime(int number) {
			return IntStream.range(2, number).allMatch(x -> (number % x) != 0);
		}
	}

}
