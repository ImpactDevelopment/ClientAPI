/*
 * Copyright 2017 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.util.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringJoiner;

/**
 * Reads from an InputStream
 *
 * @author Brady
 * @since 2/15/2017 12:00 PM
 */
public final class StreamReader {

	/**
	 * Stream being read
	 */
	private final InputStream stream;

	public StreamReader(InputStream stream) {
		this.stream = stream;
	}

	/**
	 * Reads the stream and returns the output
	 *
	 * @return The stream's output
	 */
	public final String read() {
		StringJoiner joiner = new StringJoiner("\n");
		try {
			BufferedReader br =
			    new BufferedReader(new InputStreamReader(stream));
			String line;
			while ((line = br.readLine()) != null)
				joiner.add(line);

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return joiner.toString();
	}
}
