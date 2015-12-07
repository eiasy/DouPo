#ifndef df_macro_h
#define df_macro_h

#ifdef WIN32
#include <windows.h>
#endif


inline long AtomSelfAdd(void * var)
{
#ifdef WIN32
	return InterlockedIncrement((long *)(var)); // NOLINT
#else
	return __sync_add_and_fetch((long *)(var), 1); // NOLINT
#endif
}

inline long AtomSelfDec(void * var)
{
#ifdef WIN32
	return InterlockedDecrement((long *)(var)); // NOLINT
#else
	return __sync_add_and_fetch((long *)(var), -1); // NOLINT
#endif
}

inline long AtomAdd(void * var,  long value)
{
#ifdef WIN32
	return InterlockedExchangeAdd((long *)(var), value); // NOLINT
#else
	return __sync_fetch_and_add((long *)(var), value);  // NOLINT
#endif
}

inline long AtomDec(void * var, long value)
{
	value = value * -1;
#ifdef WIN32
	return InterlockedExchangeAdd((long *)(var), value); // NOLINT
#else
	return __sync_fetch_and_add((long *)(var), value);  // NOLINT
#endif
}

inline long AtomSet(void * var, long value)
{
#ifdef WIN32
	::InterlockedExchange((long *)(var), (long)(value)); // NOLINT
#else
	__sync_lock_test_and_set((long *)(var), value);  // NOLINT
#endif
	return value;
}


inline long AtomGet(void * var)
{
#ifdef WIN32
	return InterlockedExchangeAdd((long *)(var), 0); // NOLINT
#else
	return __sync_fetch_and_add((long *)(var), 0);  // NOLINT
#endif
}

#endif  
