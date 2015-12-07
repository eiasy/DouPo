package mmo.module.logger.role;

public interface IDuplicateLog {
	interface Operate {
		String ENTER = "进入";
		String EXIT  = "退出";
	}

	interface Result {
		String PASS  = "通关";
		String EXIT  = "退出";
		String DEATH = "失败";
		String ENTER = "进入";
	}

	interface Category {
		String COMMON            = "普通";
		String HERO              = "英雄";
		String SECRET            = "秘境";
		String CHALLENGE         = "竞技场";
		String CHALLENGE_PRIVATE = "私战";
		String CHALLENGE_LINKED  = "链接竞技场";
		String BOSS              = "世界BOSS";
		String STAND             = "一战到底";
		String MONSTER           = "怪物攻城";
	}
}
