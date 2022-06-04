cd com/java/zteam/ooad/Veloxcena

for file in *:
do
    then
        echo "Committing $file 🐱‍🏍"
        git add $file
        git commit -m "Commit $file ✅"
        git push
done