## 第三周学习总结

### 一、第7课：泛型递归、树的递归

#### 1、递归（Recursion）

**1）递归代码模板（Java）背过！！！**

```

public void recur(int level, int param) {

	// 1、recursion terminater(递归终结条件)
	if (level > MAX_LEVEL) {
		// process result
		return;
	}
	
	// 2、process current logic（处理当前层逻辑）
	process(level, param);
	
	// 3、drill down （下探到下一层）
	recur(level+1, newParam);
	
	// 4、restore current status（清理当前层）
	
}

```

**2）思维要点**

* 不要人肉进行递归
* 找重复最近子问题
* 数学归纳法思维










