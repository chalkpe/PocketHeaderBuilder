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
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ChalkPE <chalkpe@gmail.com>
 * @since 2015-11-16
 */
public class PocketHeaderBuilder {
    public static final Pattern VTABLE_PATTERN = Pattern.compile("^.data.rel.ro:[0-9a-fA-F]{8}\\s+; `vtable for'(.*)$");
    public static final Pattern FUNCTION_PATTERN = Pattern.compile("^.data.rel.ro:[0-9a-fA-F]{8}\\s+DCD\\s+(.*)$");

    public static void main(String[] args) throws IOException {
        if(args.length < 1) throw new IllegalArgumentException("Empty argument");

        final Path path = Paths.get(args[0]);
        if(!Files.exists(path) || !Files.isRegularFile(path) || !Files.isReadable(path)) throw new IllegalArgumentException("Invalid path: " + path.toAbsolutePath().toString());

        Files.lines(path, StandardCharsets.UTF_8).reduce(new ArrayList<>(), (final ArrayList<Header> list, final String line) -> {
            Matcher matcher;

            if((matcher = VTABLE_PATTERN.matcher(line)).find()) list.add(new Header(matcher.group(1)));
            else if(!list.isEmpty() && (matcher = FUNCTION_PATTERN.matcher(line)).find()){
                final Header lastHeader = list.get(list.size() - 1);
                if(lastHeader != null){
                    final String function = OnlineDemangler.demangle(matcher.group(1));
                    if(function != null) lastHeader.addFunction(function);
                }
            }

            return list;
        }, (a, b) -> {
            final ArrayList<Header> list = new ArrayList<>(a.size() + b.size());
            list.addAll(a); list.addAll(b);

            return list;
        }).parallelStream().forEach(Header::save);
    }
}
