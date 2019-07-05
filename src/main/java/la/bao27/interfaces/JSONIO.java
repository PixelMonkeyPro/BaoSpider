package la.bao27.interfaces;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author Simon
 */

public interface JSONIO {

	String getFilePath();

	@SneakyThrows
	default void toFile() {
		val file = new File(getFilePath());
		if (!file.exists()) {
			if (!file.createNewFile()) {
				throw new IOException("file create fail!");
			}
		}
		@Cleanup FileOutputStream fileOut = new FileOutputStream(file);
		@Cleanup OutputStreamWriter writer = new OutputStreamWriter(fileOut, StandardCharsets.UTF_8);
		writer.write(JSON.toJSONString(this));
		writer.flush();
	}

	@SneakyThrows
	default <T> ArrayList<T> toObject(Class<T> clazz) {
		val file = new File(getFilePath());
		if (!file.exists()) {
			return null;
		}
		@Cleanup FileReader in = new FileReader(file);
		@Cleanup BufferedReader reader = new BufferedReader(in);
		val content = new StringBuilder();
		while (reader.ready()) {
			content.append(reader.readLine());
		}
		return JSON.parseObject(content.toString(), new TypeReference<ArrayList<T>>() {});
	}
}
