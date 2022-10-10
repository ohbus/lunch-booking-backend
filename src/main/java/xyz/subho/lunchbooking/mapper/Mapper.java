package xyz.subho.lunchbooking.mapper;

public interface Mapper<S, T> {

  T transform(S source);

  S transformBack(T source);
}
