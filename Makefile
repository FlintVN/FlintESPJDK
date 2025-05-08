
MODULE_NAME         :=  esp.io
JC                  :=  javac
MODULE_SOURCE_PATH  :=  src
OUTPUT_DIR          :=  bin

JAVA_BASE			:=  modules/java.base.jar

RUN_OPT             :=  
DEV_OPT             :=  -g
JFLAGS              :=  -Xlint:all                              \
                        -XDstringConcat=inline                  \
                        --system=none                           \
                        -encoding UTF-8                         \
                        --module-path="$(JAVA_BASE)"

all: run jar

run: Makefile
	@echo Compiling $(MODULE_NAME) for runtime mode
	@$(JC) $(RUN_OPT) $(JFLAGS) -d $(OUTPUT_DIR)/run --module $(MODULE_NAME) --module-source-path $(MODULE_SOURCE_PATH)

dev: Makefile
	@echo Compiling $(MODULE_NAME) for development mode
	@$(JC) $(DEV_OPT) $(JFLAGS) -d $(OUTPUT_DIR)/dev --module $(MODULE_NAME) --module-source-path $(MODULE_SOURCE_PATH)

jar: dev META-INF/MANIFEST.MF Makefile
	@jar --create --file $(OUTPUT_DIR)/dev/$(MODULE_NAME).jar --manifest META-INF/MANIFEST.MF -C $(OUTPUT_DIR)/dev/$(MODULE_NAME) "."
	@echo Created $(OUTPUT_DIR)/dev/$(MODULE_NAME).jar

clean:
	@rm -rf $(OUTPUT_DIR)
	@echo Clean complete
