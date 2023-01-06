package com.rootlab.ch10.common;

public class Constants {

	public enum ExceptionClass {

		PRODUCT("Product");

		private final String exceptionClass;

		// enum의 경우 생성자의 접근제어자가 private여야 한다.
		ExceptionClass(String exceptionClass) {
			this.exceptionClass = exceptionClass;
		}

		public String getExceptionClass() {
			return exceptionClass;
		}

		@Override
		public String toString() {
			return getExceptionClass() + " Exception: ";
		}

	}
}
