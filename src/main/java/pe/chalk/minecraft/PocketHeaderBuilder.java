/*
 * Copyright 2015 ChalkPE
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

package pe.chalk.minecraft;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author ChalkPE <chalkpe@gmail.com>
 * @since 2015-11-16
 */
public class PocketHeaderBuilder {
    public static void main(String[] args) throws IOException {
        if(args.length < 1) throw new IllegalArgumentException("Empty argument");

        Path path = Paths.get(args[0]);
        if(!Files.exists(path) || !Files.isRegularFile(path) || !Files.isReadable(path)) throw new IllegalArgumentException("Invalid path: " + path.toAbsolutePath().toString());

        Files.lines(path, StandardCharsets.UTF_8).forEach(line -> {
            //TODO: Implement this block
        });
    }
}
