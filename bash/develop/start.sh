echo "============= Start gradle build for auto reload ============="
run_started=false
nohup gradle -t build -x test | while read -r line
do
    echo "$line"
    # launch "gradle run" once when the above command puts "Waiting for changes to input files..."
    if [[ "$line" == *"Waiting for changes to input files..."* ]] && [ "$run_started" = false ]; then
          echo "============= Start gradle run ============="
          nohup gradle run &
          run_started=true
    fi
done